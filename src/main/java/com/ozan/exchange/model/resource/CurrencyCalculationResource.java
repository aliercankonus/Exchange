package com.ozan.exchange.model.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class CurrencyCalculationResource {
    private String transactionId;
    private BigDecimal result;
}
