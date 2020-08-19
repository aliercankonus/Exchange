package com.ozan.exchange.controller;

import com.ozan.exchange.TestConstants;
import com.ozan.exchange.TestUtil;
import com.ozan.exchange.model.data.ExchangeRate;
import com.ozan.exchange.model.resource.ExchangeRateResource;
import com.ozan.exchange.service.ExchangeRateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ExchangeRateControllerTest {

    @Mock private ExchangeRateService exchangeRateServiceMock;
    @Mock private ConversionService conversionServiceMock;

    @InjectMocks private ExchangeRateController underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest = new ExchangeRateController(exchangeRateServiceMock, conversionServiceMock);
    }

    @Test
    public void shouldGetExchangeRate() {
        // GIVEN
        ExchangeRate exchangeRate = TestUtil.createExchangeRate();
        ExchangeRateResource exchangeRateResource = TestUtil.createExchangeRateResource();
        when(exchangeRateServiceMock.getExchangeRates(anyString(), anyList()))
                .thenReturn(exchangeRate);
        when(conversionServiceMock.convert(any(ExchangeRate.class), eq(ExchangeRateResource.class)))
                .thenReturn(exchangeRateResource);

        // WHEN
        ExchangeRateResource result =
                underTest.getExchangeRate(TestConstants.ANY_BASE, TestConstants.ANY_SYMBOLS);

        // THEN
        assertNotNull(result);
        assertEquals(result.getBase(), exchangeRateResource.getBase());
        assertEquals(result.getRates().values(), exchangeRateResource.getRates().values());
    }
}
