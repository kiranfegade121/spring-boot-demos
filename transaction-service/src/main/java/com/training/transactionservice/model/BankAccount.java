package com.training.transactionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="bankaccounts")
@Data
public class BankAccount {
	
	enum BankAccountTypes {
		SAVING, CURRENT
	}
	
	@Id
	@Column(name="account_id")
	private int accountId;
	@Column(name = "account_holder")
	private String accountHolderName;
	@Enumerated(EnumType.STRING)
	@Column(name = "account_type")
	private BankAccountTypes accountType;
	@Column(name="account_balance")
	private double accountBalance;
	
}
