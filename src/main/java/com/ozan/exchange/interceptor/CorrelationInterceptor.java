package com.ozan.exchange.interceptor;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class CorrelationInterceptor extends HandlerInterceptorAdapter {
    private static final String CORRELATION_ID_LOG_VAR_NAME = "CORRELATION_ID";

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler) {
        MDC.clear();
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, generateUniqueCorrelationId());
        return true;
    }

    @Override
    public void afterCompletion(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final Exception ex) {
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
