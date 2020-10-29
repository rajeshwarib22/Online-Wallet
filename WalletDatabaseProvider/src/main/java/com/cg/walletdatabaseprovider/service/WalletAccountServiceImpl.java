package com.cg.walletdatabaseprovider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.walletdatabaseprovider.dao.WalletAccountDao;
import com.cg.walletdatabaseprovider.entity.WalletAccount;
import com.cg.walletdatabaseprovider.exception.WalletAccountNotFoundException;


@Service
public class WalletAccountServiceImpl {
	
	@Autowired
	WalletAccountDao walletDao;
	
	public WalletAccount createAccount(WalletAccount wa) {
		if(wa==null) {
		System.out.println("Object not created");
		throw new WalletAccountNotFoundException("object not created");
	}
		return walletDao.save(wa);
	}
	

	public List<WalletAccount> showBalance(int accountId){
		List<WalletAccount> balanceList= walletDao.findByaccountId(accountId);
		return balanceList;
		
	}
	public List<WalletAccount> allaccount(){
		return walletDao.findAll();	}
}
