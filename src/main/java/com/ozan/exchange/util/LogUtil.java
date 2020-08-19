package com.ozan.exchange.util;

import com.ozan.exchange.exception.InvalidCorrelationIdException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class LogUtil {

    private static final String LOG_ERROR_MESSAGE =
            "Correlation Id : <%s> with Exception message : <%s> and Http Status code : <%s>.";

    private LogUtil() {}

    public static String getCorrelationId() {
        String correlationId;
        try {
            correlationId = MDC.get("CORRELATION_ID");
        } catch (RuntimeException ex) {
            throw new InvalidCorrelationIdException("Exception while getting correlation id.");
        }
        return correlationId;
    }

    public static void logErrorMessage(
            String correlationId, String exceptionMessage, int httpStatusCode) {
        log.error(
                String.format(LOG_ERROR_MESSAGE, correlationId, exceptionMessage, httpStatusCode));
    }
}
