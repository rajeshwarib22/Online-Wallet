package com.cg.walletdatabaseprovider.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.walletdatabaseprovider.entity.WalletAccount;

@Repository
public interface WalletAccountDao extends JpaRepository<WalletAccount, Integer> {

	public List<WalletAccount> findByuserId(int userId);
	public List<WalletAccount> findByaccountId(int accountId);
}
