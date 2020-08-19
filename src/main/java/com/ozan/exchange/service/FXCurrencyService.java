package com.ozan.exchange.service;

import com.ozan.exchange.accessor.FXCurrencyAccessor;
import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.exception.InvalidFeignCallException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FXCurrencyService {
    private final FXCurrencyAccessor fxCurrencyAccessor;

    public FXCurrencyResource getCurrencyInfoFromServiceProvider(
            String base, List<String> symbols) {
        try {
            FXCurrencyResource fxCurrencyResource =
                    fxCurrencyAccessor.getCurrencyInfo(base, symbols);
            log.info(
                    "Exchange rate is received for base: {} and rates: {}",
                    fxCurrencyResource.getBase(),
                    fxCurrencyResource.getRates());
            return fxCurrencyResource;
        } catch (FeignException ex) {
            throw new InvalidFeignCallException(ex.getMessage());
        }
    }
}
