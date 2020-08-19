package com.ozan.exchange.config;

import com.ozan.exchange.converter.CurrencyCalculationEntityToCurrencyCalculationConverter;
import com.ozan.exchange.converter.CurrencyCalculationToCurrencyCalculationResourceConverter;
import com.ozan.exchange.converter.ExchangeRateToExchangeRateResourceConverter;
import com.ozan.exchange.interceptor.CorrelationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ExchangeRateToExchangeRateResourceConverter());
        registry.addConverter(new CurrencyCalculationToCurrencyCalculationResourceConverter());
        registry.addConverter(new CurrencyCalculationEntityToCurrencyCalculationConverter());
    }
}
