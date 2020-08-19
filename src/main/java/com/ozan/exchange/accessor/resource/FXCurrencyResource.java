package com.ozan.exchange.accessor.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
public class FXCurrencyResource {
    private String base;
    private Map<String, BigDecimal> rates;
    private String date;
}
