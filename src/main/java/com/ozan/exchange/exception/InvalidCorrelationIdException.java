package com.ozan.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCorrelationIdException extends GlobalResponseException {

    public InvalidCorrelationIdException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
