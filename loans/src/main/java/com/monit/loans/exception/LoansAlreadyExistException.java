package com.monit.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_GATEWAY)
public class LoansAlreadyExistException extends RuntimeException {
    public LoansAlreadyExistException(String message) {
        super(message);
    }
}
