package com.ozan.exchange.controller;

import com.ozan.exchange.model.resource.ExchangeRateResource;
import com.ozan.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rate")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;
    private final ConversionService conversionService;

    @GetMapping
    public ExchangeRateResource getExchangeRate(
            @RequestParam(required = false, defaultValue = "USD") String base,
            @RequestParam(required = false) List<String> symbols) {
        return conversionService.convert(
                exchangeRateService.getExchangeRates(base, symbols), ExchangeRateResource.class);
    }
}
