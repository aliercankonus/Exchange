package com.ozan.exchange.service;

import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.model.data.ExchangeRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {
    private final FXCurrencyService fxCurrencyService;

    public ExchangeRate getExchangeRates(String base, List<String> symbols) {
        FXCurrencyResource fxCurrencyResource =
                fxCurrencyService.getCurrencyInfoFromServiceProvider(base, symbols);
        return ExchangeRate.builder()
                .base(fxCurrencyResource.getBase())
                .rates(fxCurrencyResource.getRates())
                .build();
    }
}
