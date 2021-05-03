package com.training.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.transactionservice.model.BankAccount;

public interface TransactionRepository extends JpaRepository<BankAccount, Integer> {

}
