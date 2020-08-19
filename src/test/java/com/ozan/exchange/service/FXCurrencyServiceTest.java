package com.ozan.exchange.service;

import com.ozan.exchange.TestConstants;
import com.ozan.exchange.TestUtil;
import com.ozan.exchange.accessor.FXCurrencyAccessor;
import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import com.ozan.exchange.exception.InvalidFeignCallException;
import feign.FeignException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FXCurrencyServiceTest {

    @Mock private FXCurrencyAccessor fxCurrencyAccessorMock;
    private FXCurrencyService underTest;

    @BeforeMethod
    public void setup() {

        MockitoAnnotations.initMocks(this);
        underTest = new FXCurrencyService(fxCurrencyAccessorMock);
    }

    @Test
    public void shouldGetExchangeRateFromServiceProvider() {
        // GIVEN
        FXCurrencyResource fxCurrencyResource = TestUtil.createFXCurrencyResource();
        when(fxCurrencyAccessorMock.getCurrencyInfo(anyString(), anyList()))
                .thenReturn(fxCurrencyResource);

        // WHEN
        FXCurrencyResource result =
                underTest.getCurrencyInfoFromServiceProvider(
                        TestConstants.ANY_BASE, TestConstants.ANY_SYMBOLS);

        // THEN
        assertNotNull(result);
        assertEquals(result.getBase(), fxCurrencyResource.getBase());
        assertEquals(result.getDate(), fxCurrencyResource.getDate());
        assertEquals(result.getRates(), fxCurrencyResource.getRates());
    }

    @Test(expectedExceptions = InvalidFeignCallException.class)
    public void shouldThrowExceptionWhenServiceProviderDoesntWork() {
        // GIVEN
        when(fxCurrencyAccessorMock.getCurrencyInfo(anyString(), anyList()))
                .thenThrow(FeignException.class);

        // WHEN
        underTest.getCurrencyInfoFromServiceProvider(
                TestConstants.ANY_BASE, TestConstants.ANY_SYMBOLS);
    }
}
