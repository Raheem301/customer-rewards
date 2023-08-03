package com.example.rewards.controller;

import com.example.rewards.exception.CustomException;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @MockBean
    private RewardService rewardService;

    private final MockMvc mockMvc;

    public RewardControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testCalculateRewardPoints() throws Exception {
        Transaction transaction = new Transaction("customerId1", 120);
        when(rewardService.calculateRewardPoints(any(Transaction.class))).thenReturn(90);

        mockMvc.perform(MockMvcRequestBuilders.post("/rewards/transactions")
                .content(asJsonString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(rewardService, times(1)).calculateRewardPoints(any(Transaction.class));
    }
    
    @Test
    public void testCalculateRewardPoints_invalidTransaction() throws Exception {
        Transaction transaction = new Transaction("customerId1", 0);
        when(rewardService.calculateRewardPoints(any(Transaction.class)))
                .thenThrow(new CustomException("Transaction amount must be greater than zero"));

        mockMvc.perform(MockMvcRequestBuilders.post("/rewards/transactions")
                .content(asJsonString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(rewardService, times(1)).calculateRewardPoints(any(Transaction.class));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
