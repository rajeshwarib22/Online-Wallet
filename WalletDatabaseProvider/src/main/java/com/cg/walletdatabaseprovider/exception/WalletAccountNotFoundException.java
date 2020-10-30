package com.cg.walletdatabaseprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WalletAccountNotFoundException extends Exception{

	public WalletAccountNotFoundException() {
		super();
	}
	
	public WalletAccountNotFoundException(String message) {
		super(message);
	}

}
