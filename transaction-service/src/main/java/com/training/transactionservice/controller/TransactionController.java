package com.training.transactionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/accounts/{accountId}/balance")
	public ResponseEntity<Double> checkBankAccountBalance(@PathVariable int accountId) {		
		double balance = transactionService.checkBankAccountBalance(accountId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Boolean> withdrawOperation(@RequestParam int accountId, @RequestParam double amount) {
		boolean status = transactionService.withdraw(accountId, amount);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<Boolean> depositOperation(@RequestParam int accountId, @RequestParam double amount) {
		boolean status = transactionService.deposit(accountId, amount);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@PostMapping("/fundtransfer")
	public ResponseEntity<Boolean> fundTransferOperation(@RequestParam int fromAccountId, @RequestParam int toAccountId, @RequestParam double amount) {
		boolean status = transactionService.fundTransfer(fromAccountId, toAccountId, amount);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	
}













