package com.ozan.exchange.converter;

import com.ozan.exchange.TestUtil;
import com.ozan.exchange.model.data.CurrencyCalculation;
import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CurrencyCalculationEntityToCurrencyCalculationConverterTest {

    @InjectMocks private CurrencyCalculationEntityToCurrencyCalculationConverter underTest;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConvertCurrencyCalculationEntityToCurrencyCalculation() {
        // GIVEN
        CurrencyCalculationEntity currencyCalculationEntity =
                TestUtil.createCurrencyCalculationEntity();

        // WHEN
        CurrencyCalculation result = underTest.convert(currencyCalculationEntity);

        // THEN
        assertNotNull(result);
        assertEquals(
                result.getResult(),
                currencyCalculationEntity
                        .getAmount()
                        .multiply(currencyCalculationEntity.getRate()));
        assertEquals(result.getTransactionId(), currencyCalculationEntity.getTransactionId());
    }
}
