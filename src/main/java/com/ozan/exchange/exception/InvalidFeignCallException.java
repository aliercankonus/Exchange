package com.ozan.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFeignCallException extends GlobalResponseException {

    public InvalidFeignCallException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
