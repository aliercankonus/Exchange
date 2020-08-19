package com.ozan.exchange.converter;

import com.ozan.exchange.model.data.ExchangeRate;
import com.ozan.exchange.model.resource.ExchangeRateResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateToExchangeRateResourceConverter
        implements Converter<ExchangeRate, ExchangeRateResource> {
    @Override
    public ExchangeRateResource convert(ExchangeRate source) {
        return ExchangeRateResource.builder()
                .base(source.getBase())
                .rates(source.getRates())
                .build();
    }
}
