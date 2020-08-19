package com.ozan.exchange.service;

import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.exception.InvalidRequestException;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import com.ozan.exchange.repository.CurrencyCalculationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyCalculationService {
    private final FXCurrencyService fxCurrencyService;
    private final CurrencyCalculationRepository currencyCalculationRepository;
    private final ConversionService conversionService;

    public CurrencyCalculation calculateAndSave(String source, String target, BigDecimal amount) {
        FXCurrencyResource fxCurrencyResource =
                fxCurrencyService.getCurrencyInfoFromServiceProvider(
                        source, Collections.singletonList(target));
        BigDecimal rate = fxCurrencyResource.getRates().get(target);
        CurrencyCalculationEntity entity =
                CurrencyCalculationEntity.builder()
                        .amount(amount)
                        .rate(rate)
                        .sourceCurrency(source)
                        .targetCurrency(target)
                        .date(new Date())
                        .build();
        return conversionService.convert(
                currencyCalculationRepository.save(entity), CurrencyCalculation.class);
    }

    public Page<CurrencyCalculation> getCurrencyCalculationList(
            String transactionId, Date date, Pageable pageable) {
        if (StringUtils.isEmpty(transactionId) && ObjectUtils.isEmpty(date)) {
            throw new InvalidRequestException(
                    "At least one of date or transaction id must be provided!");
        }
        log.info(
                "Get currency calculations with transaction id: {}, date: {}", transactionId, date);
        Page<CurrencyCalculationEntity> currencyCalculationEntityPage =
                currencyCalculationRepository.findByTransactionIdAndDate(
                        transactionId, date, pageable);

        return currencyCalculationEntityPage.map(
                currencyCalculationEntity ->
                        conversionService.convert(
                                currencyCalculationEntity, CurrencyCalculation.class));
    }
}
