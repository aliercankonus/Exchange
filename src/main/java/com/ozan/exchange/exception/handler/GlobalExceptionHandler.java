package com.ozan.exchange.exception.handler;

import com.ozan.exchange.model.resource.ErrorResource;
import com.ozan.exchange.util.LogUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

public interface GlobalExceptionHandler {

    default ResponseEntity<Object> handleException(
            WebRequest request, String message, int httpStatusCode) {
        String correlationId = LogUtil.getCorrelationId();
        String exceptionMessage = "Exception occurred while system is running.";
        if (!StringUtils.isEmpty(message)) {
            exceptionMessage = message;
        }

        ErrorResource errorResource = new ErrorResource(correlationId, exceptionMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus status = HttpStatus.valueOf(httpStatusCode);
        LogUtil.logErrorMessage(correlationId, exceptionMessage, httpStatusCode);

        return new ResponseEntity<>(errorResource, headers, status);
    }
}
