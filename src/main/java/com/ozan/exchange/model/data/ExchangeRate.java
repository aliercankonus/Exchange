package com.ozan.exchange.model.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
public class ExchangeRate {
    private String base;
    private Map<String, BigDecimal> rates;
}
