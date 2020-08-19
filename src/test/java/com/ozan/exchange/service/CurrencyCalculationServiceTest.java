package com.ozan.exchange.service;

import com.ozan.exchange.TestConstants;
import com.ozan.exchange.TestUtil;
import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.exception.InvalidRequestException;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import com.ozan.exchange.repository.CurrencyCalculationRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CurrencyCalculationServiceTest {
    @Mock private FXCurrencyService fxCurrencyServiceMock;
    @Mock private CurrencyCalculationRepository currencyCalculationRepositoryMock;
    @Mock private ConversionService conversionServiceMock;

    private CurrencyCalculationService underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest =
                new CurrencyCalculationService(
                        fxCurrencyServiceMock,
                        currencyCalculationRepositoryMock,
                        conversionServiceMock);
    }

    @Test
    public void shouldCalculateAmountAndSaveIntoRepository() {
        // GIVEN
        FXCurrencyResource fxCurrencyResource = TestUtil.createFXCurrencyResource();
        CurrencyCalculationEntity currencyCalculationEntity =
                TestUtil.createCurrencyCalculationEntity();
        CurrencyCalculation currencyCalculation = TestUtil.createCurrencyCalculation();

        when(fxCurrencyServiceMock.getCurrencyInfoFromServiceProvider(anyString(), anyList()))
                .thenReturn(fxCurrencyResource);
        when(currencyCalculationRepositoryMock.save(any(CurrencyCalculationEntity.class)))
                .thenReturn(currencyCalculationEntity);
        when(conversionServiceMock.convert(
                        any(CurrencyCalculationEntity.class), eq(CurrencyCalculation.class)))
                .thenReturn(currencyCalculation);

        // WHEN
        CurrencyCalculation result =
                underTest.calculateAndSave(
                        TestConstants.ANY_BASE, TestConstants.ANY_TARGET, TestConstants.ANY_AMOUNT);

        // THEN
        assertNotNull(result);
        assertEquals(result.getResult(), currencyCalculation.getResult());
        assertEquals(result.getTransactionId(), currencyCalculation.getTransactionId());
    }

    @Test
    public void shouldGetCurrencyCalculationList() {
        Pageable pageable = PageRequest.of(5, 5);
        CurrencyCalculationEntity currencyCalculationEntity =
                TestUtil.createCurrencyCalculationEntity();
        CurrencyCalculation currencyCalculation = TestUtil.createCurrencyCalculation();
        Page<CurrencyCalculationEntity> currencyCalculationEntityPage =
                new PageImpl<>(Collections.singletonList(currencyCalculationEntity));

        when(currencyCalculationRepositoryMock.findByTransactionIdAndDate(
                        anyString(), any(Date.class), any(Pageable.class)))
                .thenReturn(currencyCalculationEntityPage);
        when(conversionServiceMock.convert(
                        any(CurrencyCalculationEntity.class), eq(CurrencyCalculation.class)))
                .thenReturn(currencyCalculation);

        // WHEN
        Page<CurrencyCalculation> result =
                underTest.getCurrencyCalculationList(
                        TestConstants.ANY_TRANSACTION_ID, TestConstants.ANY_DATE, pageable);

        // THEN
        assertNotNull(result);
        assertEquals(
                result.getContent().get(0).getTransactionId(),
                currencyCalculationEntityPage.getContent().get(0).getTransactionId());
    }

    @Test(expectedExceptions = InvalidRequestException.class)
    public void shouldThrowExceptionWhenTransactionIdAndDateIsNull() {
        // WHEN
        underTest.getCurrencyCalculationList(null, null, null);
    }
}
