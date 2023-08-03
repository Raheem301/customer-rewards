package com.example.rewards.model;

import java.time.LocalDate;

public class Transaction {

    private String customerId;
    private double amount;
    private LocalDate date;
    
    public Transaction() {
    }
    
	public Transaction(String customerId, int amount) {
		this.customerId = customerId;
		this.amount = amount;
		this.date = LocalDate.now();
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

    
}
