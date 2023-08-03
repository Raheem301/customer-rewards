package com.example.rewards.controller;

import com.example.rewards.exception.CustomException;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {
    
    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> calculateRewardPoints(@RequestBody Transaction transaction) {
        try {
            int points = rewardService.calculateRewardPoints(transaction);
            return ResponseEntity.ok(points);
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
