package com.cg.walletdatabaseprovider.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.walletdatabaseprovider.dao.WalletAccountDao;
import com.cg.walletdatabaseprovider.entity.WalletAccount;

@Service
public class WalletAccountServiceImpl {

	@Autowired
	WalletAccountDao walletDao;

	public WalletAccount createAccount(WalletAccount wa) {
		return walletDao.save(wa);
	}

	@PostConstruct
	public void createAccount() {

		WalletAccount wa = new WalletAccount(1, 0.00, "active");
		WalletAccount wa1 = new WalletAccount(2, 0.00, "inactive");
		WalletAccount wa2 = new WalletAccount(3, 0.00, "active");
		WalletAccount wa3 = new WalletAccount(4, 0.00, "active");
		WalletAccount wa4 = new WalletAccount(8, 0.00, "active");
		walletDao.save(wa);
		walletDao.save(wa1);
		walletDao.save(wa2);
		walletDao.save(wa3);
		walletDao.save(wa4);
	}

	public List<WalletAccount> showBalance(int accountId) {
		List<WalletAccount> balanceList = walletDao.findByaccountId(accountId);
		return balanceList;

	}

	public List<WalletAccount> allaccount() {
		return walletDao.findAll();
	}
}
