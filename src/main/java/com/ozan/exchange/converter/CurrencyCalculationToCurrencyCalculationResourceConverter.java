package com.ozan.exchange.converter;

import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.resource.CurrencyCalculationResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyCalculationToCurrencyCalculationResourceConverter
        implements Converter<CurrencyCalculation, CurrencyCalculationResource> {
    @Override
    public CurrencyCalculationResource convert(CurrencyCalculation source) {
        return CurrencyCalculationResource.builder()
                .result(source.getResult())
                .transactionId(source.getTransactionId())
                .build();
    }
}
