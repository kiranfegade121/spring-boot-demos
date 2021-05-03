package com.training.transactionservice.service;

public interface TransactionService {
	
	public double checkBankAccountBalance(int accountId);
	public boolean withdraw(int accountId, double amount);
	public boolean deposit(int accountId, double amount);
	public boolean fundTransfer(int fromAccountId, int toAccountId, double amount);
}
