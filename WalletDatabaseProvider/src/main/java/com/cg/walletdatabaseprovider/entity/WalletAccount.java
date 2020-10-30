package com.cg.walletdatabaseprovider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WalletAccount")
public class WalletAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	@Column
	private int userId;
	@Column
	private double accountBalance;
	@Column
	private String status;
	

	public WalletAccount() {
		super();
	}
	

	public WalletAccount(int userId, double accountBalance, String status) {
		super();
		this.userId = userId;
		this.accountBalance = accountBalance;
		this.status = status;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


}
