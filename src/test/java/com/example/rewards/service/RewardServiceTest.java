package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import org.junit.jupiter.api.Test;
import com.example.rewards.exception.CustomException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class RewardServiceTest {

    private final RewardService rewardService = new RewardService();

    @Test
    public void testCalculateRewardPoints_lessThan50() {
        Transaction transaction = new Transaction("customerId1", 40);
        assertEquals(0, rewardService.calculateRewardPoints(transaction));
    }

    @Test
    public void testCalculateRewardPoints_between50And100() {
        Transaction transaction = new Transaction("customerId1", 75);
        assertEquals(25, rewardService.calculateRewardPoints(transaction));
    }

    @Test
    public void testCalculateRewardPoints_above100() {
        Transaction transaction = new Transaction("customerId1", 120);
        assertEquals(90, rewardService.calculateRewardPoints(transaction));
    }
    
    @Test
    public void testCalculateRewardPoints_lessThanOrEqualToZero() {
        Exception exception = assertThrows(CustomException.class, () -> {
            Transaction transaction = new Transaction("customerId1", 0);
            rewardService.calculateRewardPoints(transaction);
        });

        String expectedMessage = "Transaction amount must be greater than zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
