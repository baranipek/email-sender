package com.email.sender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class OperationIsStillInProgressException extends Exception {
    private static final long serialVersionUID = 1465155085963082136L;
    public OperationIsStillInProgressException(String message) {super(message);}
}
