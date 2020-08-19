package com.ozan.exchange;

import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.data.ExchangeRate;
import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import com.ozan.exchange.model.resource.CurrencyCalculationResource;
import com.ozan.exchange.model.resource.ExchangeRateResource;

import java.math.BigDecimal;
import java.util.Map;

public class TestUtil {

    public static FXCurrencyResource createFXCurrencyResource() {
        return FXCurrencyResource.builder()
                .base(TestConstants.ANY_BASE)
                .date(TestConstants.ANY_STRING_DATE)
                .rates(Map.of("USD", BigDecimal.ONE, "EUR", BigDecimal.TEN, "GBP", BigDecimal.ONE))
                .build();
    }

    public static CurrencyCalculationEntity createCurrencyCalculationEntity() {
        return CurrencyCalculationEntity.builder()
                .transactionId(TestConstants.ANY_TRANSACTION_ID)
                .sourceCurrency(TestConstants.ANY_BASE)
                .targetCurrency(TestConstants.ANY_TARGET)
                .rate(TestConstants.ANY_RATE)
                .amount(TestConstants.ANY_AMOUNT)
                .date(TestConstants.ANY_DATE)
                .build();
    }

    public static CurrencyCalculation createCurrencyCalculation() {
        return CurrencyCalculation.builder()
                .transactionId(TestConstants.ANY_TRANSACTION_ID)
                .result(TestConstants.ANY_AMOUNT)
                .build();
    }

    public static CurrencyCalculationResource createCurrencyCalculationResource() {
        return CurrencyCalculationResource.builder()
                .transactionId(TestConstants.ANY_TRANSACTION_ID)
                .result(TestConstants.ANY_AMOUNT)
                .build();
    }

    public static ExchangeRate createExchangeRate() {
        return ExchangeRate.builder()
                .base(TestConstants.ANY_BASE)
                .rates(Map.of("USD", BigDecimal.ONE, "EUR", BigDecimal.TEN, "GBP", BigDecimal.ONE))
                .build();
    }

    public static ExchangeRateResource createExchangeRateResource() {
        return ExchangeRateResource.builder()
                .base(TestConstants.ANY_BASE)
                .rates(Map.of("USD", BigDecimal.ONE, "EUR", BigDecimal.TEN, "GBP", BigDecimal.ONE))
                .build();
    }
}
