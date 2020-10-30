package com.cg.walletdatabaseprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	@ExceptionHandler(value = WalletAccountNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handlingNoValueFoundException(WalletAccountNotFoundException e,WebRequest we ) {
		ExceptionResponse error = new ExceptionResponse();
		error.setException(" " + e.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(error, status);
	}

}
