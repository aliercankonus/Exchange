package com.ozan.exchange.model.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
public class ExchangeRateResource {
    private String base;
    private Map<String, BigDecimal> rates;
}
