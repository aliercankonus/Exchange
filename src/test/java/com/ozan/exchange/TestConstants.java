package com.ozan.exchange;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestConstants {
    public static final String ANY_TRANSACTION_ID = UUID.randomUUID().toString();
    public static final String ANY_BASE = "USD";
    public static final String ANY_TARGET = "GBP";
    public static final BigDecimal ANY_RATE = BigDecimal.ONE;
    public static final BigDecimal ANY_AMOUNT = BigDecimal.TEN;
    public static final String ANY_STRING_DATE = "18-08-2020";
    public static final Date ANY_DATE = Date.from(Instant.now());
    public static final List<String> ANY_SYMBOLS = Arrays.asList("CHF", "EUR", "GBP");
}
