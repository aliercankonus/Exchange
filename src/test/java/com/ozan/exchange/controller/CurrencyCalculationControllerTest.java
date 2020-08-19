package com.ozan.exchange.controller;

import com.ozan.exchange.TestConstants;
import com.ozan.exchange.TestUtil;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.resource.CurrencyCalculationResource;
import com.ozan.exchange.service.CurrencyCalculationService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CurrencyCalculationControllerTest {

    @Mock private CurrencyCalculationService currencyCalculationServiceMock;
    @Mock private ConversionService conversionServiceMock;

    private CurrencyCalculationController underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest =
                new CurrencyCalculationController(
                        currencyCalculationServiceMock, conversionServiceMock);
    }

    @Test
    public void shouldGetCurrencyCalculation() {
        // GIVEN
        CurrencyCalculation currencyCalculation = TestUtil.createCurrencyCalculation();
        CurrencyCalculationResource currencyCalculationResource =
                TestUtil.createCurrencyCalculationResource();
        when(currencyCalculationServiceMock.calculateAndSave(
                        anyString(), anyString(), any(BigDecimal.class)))
                .thenReturn(currencyCalculation);
        when(conversionServiceMock.convert(
                        any(CurrencyCalculation.class), eq(CurrencyCalculationResource.class)))
                .thenReturn(currencyCalculationResource);

        // WHEN
        CurrencyCalculationResource result =
                underTest.getCurrencyCalculation(
                        TestConstants.ANY_BASE, TestConstants.ANY_TARGET, TestConstants.ANY_AMOUNT);
        // THEN
        assertNotNull(result);
        assertEquals(result.getTransactionId(), currencyCalculationResource.getTransactionId());
        assertEquals(result.getResult(), currencyCalculationResource.getResult());
    }

    @Test
    public void shouldGetCurrencyCalculationList() {
        // GIVEN
        Pageable pageable = PageRequest.of(5, 5);
        CurrencyCalculation currencyCalculation = TestUtil.createCurrencyCalculation();
        CurrencyCalculationResource currencyCalculationResource =
                TestUtil.createCurrencyCalculationResource();
        Page<CurrencyCalculation> currencyCalculationPage =
                new PageImpl<>(Collections.singletonList(currencyCalculation));
        when(currencyCalculationServiceMock.getCurrencyCalculationList(
                        anyString(), any(Date.class), any(Pageable.class)))
                .thenReturn(currencyCalculationPage);
        when(conversionServiceMock.convert(
                        any(CurrencyCalculation.class), eq(CurrencyCalculationResource.class)))
                .thenReturn(currencyCalculationResource);

        // WHEN
        Page<CurrencyCalculationResource> result =
                underTest.getCurrencyCalculationList(
                        TestConstants.ANY_TRANSACTION_ID, TestConstants.ANY_DATE, pageable);
        // THEN
        assertNotNull(result);
        assertEquals(
                result.getContent().get(0).getTransactionId(),
                currencyCalculationPage.getContent().get(0).getTransactionId());
    }
}
