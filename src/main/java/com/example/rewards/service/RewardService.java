package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import com.example.rewards.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    public int calculateRewardPoints(Transaction transaction) {
        int points = 0;

        if (transaction.getAmount() <= 0) {
            throw new CustomException("Transaction amount must be greater than zero");
        }

        if (transaction.getAmount() > 100) {
            points += 2 * (transaction.getAmount() - 100) + 50;
        } else if (transaction.getAmount() > 50) {
            points += transaction.getAmount() - 50;
        }

        return points;
    }
}
