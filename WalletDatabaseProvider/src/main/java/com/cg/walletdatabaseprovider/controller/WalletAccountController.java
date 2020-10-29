package com.cg.walletdatabaseprovider.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.walletdatabaseprovider.entity.WalletAccount;
import com.cg.walletdatabaseprovider.exception.WalletAccountNotFoundException;
import com.cg.walletdatabaseprovider.service.WalletAccountServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "WalletController swagger")
public class WalletAccountController {

	@Autowired
	WalletAccountServiceImpl walletService;
	
	private static final Logger logger = LoggerFactory.getLogger(WalletAccountController.class);

	@PostMapping("/addWalletAccount")
	@ApiOperation(value = "addWallet", nickname = "AddWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public void addWallet(@RequestBody WalletAccount wa) {
		System.out.println("inside addAccount() of Controller");
		if(wa.toString().equals("{}")) {
			throw new WalletAccountNotFoundException("object not created properly");
			
		}
		walletService.createAccount(wa);
		
	}
	

	

	@GetMapping("/accountinfom")
	@ApiOperation(value = "showAccount", nickname = "AddWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public List<WalletAccount> showAccount() {

		List<WalletAccount> account = walletService.allaccount();
		
		return account;
	}
	
	
	@GetMapping("/accountinfo/{accountId}")
	@ApiOperation(value = "showAccountBalance", nickname = "AddWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public List<WalletAccount> showAccountBalance(@PathVariable(required = false) int accountId) {

		List<WalletAccount> account = walletService.showBalance(accountId);
		if (account == null) {
			System.out.println("going to throw an Exception");
			throw new WalletAccountNotFoundException("Account id"+accountId+"not found");
		}
		return account;
	}
	
	@PutMapping("/accountid/{accountId}/amount/{amount}")
	@ApiOperation(value = "updateAccountBalance", nickname = "UpdateWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public void updateAccountBalance(@PathVariable(value="accountId") int accountId,@PathVariable(value="amount") int amount) {

		List<WalletAccount> wallet = walletService.showBalance(accountId);
			    
		for(WalletAccount w : wallet) {
			  double b=w.getAccountBalance();
			  b+= amount;
			  w.setAccountBalance(b);
			  walletService.createAccount(w);
			  }

	}
}
