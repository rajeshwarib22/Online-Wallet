package com.cg.walletdatabaseprovider.controller;

/**
 * Author : Rajeshwari Bhirud
 * Date 266/10/2020
 * 
 *
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

	
	@GetMapping("/accountinfo/{accountId}")
	@ApiOperation(value = "showAccountBalance", nickname = "AddWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public List<WalletAccount> showAccountBalance(@PathVariable(required = true) int accountId) throws WalletAccountNotFoundException {

		List<WalletAccount> account = walletService.showBalance(accountId);
		if (account.isEmpty()) {
			System.out.println("going to throw an Exception");
			throw new WalletAccountNotFoundException("Account id"+accountId+"not found");
		}
		return account ;
	}
	
	@PutMapping("/accountid/{accountId}/amount/{amount}")
	@ApiOperation(value = "updateAccountBalance", nickname = "UpdateWalletAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WalletAccount.class),
			@ApiResponse(code = 500, message = "Failure", response = WalletAccount.class) })
	public void updateAccountBalance(@PathVariable(value="accountId") int accountId,@PathVariable(value="amount") int amount) throws WalletAccountNotFoundException {

		List<WalletAccount> wallet = walletService.showBalance(accountId);
			 
		if (wallet.isEmpty()) {
			System.out.println("going to throw an Exception");
			throw new WalletAccountNotFoundException("Account with id "+accountId+" does not exists");
		}
		
		for(WalletAccount w : wallet) {
			  if(w.getUserId() != accountId) {
				  throw new WalletAccountNotFoundException("User id and account id do not match");
			  }
			  if(w.getStatus().equalsIgnoreCase("inactive")) {
				  throw new WalletAccountNotFoundException("Wallet account is inactive user cannot add money");
			  }
//			  if(amount>100000) {
//				  throw new WalletAccountNotFoundException("add amount ");
//			  }
			
			  double b=w.getAccountBalance();
			  b+= amount;
			  w.setAccountBalance(b);
			  walletService.createAccount(w);
			  }

	}
}
