package com.training.transactionservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.transactionservice.model.BankAccount;
import com.training.transactionservice.repository.TransactionRepository;
import com.training.transactionservice.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public double checkBankAccountBalance(int accountId) {
		Optional<BankAccount> result = transactionRepository.findById(accountId);
		return result.get().getAccountBalance();
	}

	@Override
	public boolean withdraw(int accountId, double amount) {
		Optional<BankAccount> result = transactionRepository.findById(accountId);
		BankAccount account = result.get();
		account.setAccountBalance(account.getAccountBalance() - amount);
		transactionRepository.save(account);
		return true;
	}

	@Override
	public boolean deposit(int accountId, double amount) {
		Optional<BankAccount> result = transactionRepository.findById(accountId);
		BankAccount account = result.get();
		account.setAccountBalance(account.getAccountBalance() + amount);
		transactionRepository.save(account);
		return true;
	}

	@Override
	public boolean fundTransfer(int fromAccountId, int toAccountId, double amount) {
		withdraw(fromAccountId, amount);
		deposit(toAccountId, amount);
		return true;
	}

}
