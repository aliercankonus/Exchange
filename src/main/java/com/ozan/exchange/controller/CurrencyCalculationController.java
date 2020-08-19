package com.ozan.exchange.controller;

import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.resource.CurrencyCalculationResource;
import com.ozan.exchange.service.CurrencyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class CurrencyCalculationController {
    private final CurrencyCalculationService currencyCalculationService;
    private final ConversionService conversionService;

    @GetMapping("/convert")
    public CurrencyCalculationResource getCurrencyCalculation(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount) {
        return conversionService.convert(
                currencyCalculationService.calculateAndSave(from, to, amount),
                CurrencyCalculationResource.class);
    }

    @GetMapping("/list")
    public Page<CurrencyCalculationResource> getCurrencyCalculationList(
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
            @PageableDefault Pageable pageable) {
        Page<CurrencyCalculation> currencyCalculationPage =
                currencyCalculationService.getCurrencyCalculationList(
                        transactionId, date, pageable);
        return currencyCalculationPage.map(
                currencyCalculation ->
                        conversionService.convert(
                                currencyCalculation, CurrencyCalculationResource.class));
    }
}
