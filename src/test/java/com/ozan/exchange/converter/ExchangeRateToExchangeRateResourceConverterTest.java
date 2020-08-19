package com.ozan.exchange.converter;

import com.ozan.exchange.TestUtil;
import com.ozan.exchange.model.data.ExchangeRate;
import com.ozan.exchange.model.resource.ExchangeRateResource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ExchangeRateToExchangeRateResourceConverterTest {

    @InjectMocks private ExchangeRateToExchangeRateResourceConverter underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConvertExchangeRateToExchangeRateResource() {
        // GIVEN
        ExchangeRate exchangeRate = TestUtil.createExchangeRate();

        // WHEN
        ExchangeRateResource result = underTest.convert(exchangeRate);

        // THEN
        assertNotNull(result);
        assertEquals(result.getBase(), exchangeRate.getBase());
        assertEquals(result.getRates().values(), exchangeRate.getRates().values());
    }
}
