package com.ozan.exchange.converter;

import com.ozan.exchange.TestUtil;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.resource.CurrencyCalculationResource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CurrencyCalculationToCurrencyCalculationResourceConverterTest {

    @InjectMocks private CurrencyCalculationToCurrencyCalculationResourceConverter underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConvertCurrencyCalculationToCurrencyCalculationResource() {
        // GIVEN
        CurrencyCalculation currencyCalculation = TestUtil.createCurrencyCalculation();

        // WHEN
        CurrencyCalculationResource result = underTest.convert(currencyCalculation);

        // THEN
        assertNotNull(result);
        assertEquals(result.getResult(), currencyCalculation.getResult());
        assertEquals(result.getTransactionId(), currencyCalculation.getTransactionId());
    }
}
