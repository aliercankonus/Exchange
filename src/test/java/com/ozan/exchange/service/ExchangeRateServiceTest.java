package com.ozan.exchange.service;

import com.ozan.exchange.TestConstants;
import com.ozan.exchange.TestUtil;
import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.model.data.ExchangeRate;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ExchangeRateServiceTest {

    @Mock private FXCurrencyService fxCurrencyServiceMock;

    private ExchangeRateService underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest = new ExchangeRateService(fxCurrencyServiceMock);
    }

    @Test
    public void shouldGetExchangeRateWhenServiceProviderFetchDataSuccessfully() {
        // GIVEN
        FXCurrencyResource fxCurrencyResource = TestUtil.createFXCurrencyResource();
        when(fxCurrencyServiceMock.getCurrencyInfoFromServiceProvider(anyString(), anyList()))
                .thenReturn(fxCurrencyResource);

        // WHEN
        ExchangeRate result =
                underTest.getExchangeRates(TestConstants.ANY_BASE, TestConstants.ANY_SYMBOLS);

        // THEN
        assertNotNull(result);
        assertEquals(result.getBase(), fxCurrencyResource.getBase());
        assertEquals(result.getRates().get(0), fxCurrencyResource.getRates().get(0));
    }
}
