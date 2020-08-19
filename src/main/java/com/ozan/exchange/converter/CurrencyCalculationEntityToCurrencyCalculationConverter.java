package com.ozan.exchange.converter;

import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyCalculationEntityToCurrencyCalculationConverter
        implements Converter<CurrencyCalculationEntity, CurrencyCalculation> {
    @Override
    public CurrencyCalculation convert(CurrencyCalculationEntity source) {
        return CurrencyCalculation.builder()
                .result(source.getAmount().multiply(source.getRate()))
                .transactionId(source.getTransactionId())
                .build();
    }
}
