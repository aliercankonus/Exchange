package com.ozan.exchange.exception.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.ozan.exchange.exception.GlobalResponseException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExchangeExceptionHandler implements GlobalExceptionHandler {

    @ExceptionHandler(GlobalResponseException.class)
    public ResponseEntity<Object> handleGlobalResponseException(
            WebRequest request, GlobalResponseException ex) {
        return handleException(request, ex.getMessage(), ex.getId());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            WebRequest request, IllegalArgumentException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleJsonParseException(
            WebRequest request, JsonParseException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailedException(
            WebRequest request, ConversionFailedException ex) {
        return handleException(request, ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(WebRequest request, Exception ex) {
        return handleException(
                request, ex.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
