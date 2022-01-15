package com.calculator.coin.exception;

import com.calculator.coin.enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionContextTest {

    CurrencyConversionContext validContext;
    CurrencyConversionContext invalidContextMin;
    CurrencyConversionContext invalidContextMax;


    @BeforeEach
    void setup() {
        validContext = CurrencyConversionContext.of(Double.valueOf(1000), Currency.USD, Currency.BTC);
        invalidContextMin = CurrencyConversionContext.of(Double.valueOf(10), Currency.USD, Currency.BTC);
        invalidContextMax = CurrencyConversionContext.of(Double.valueOf(10000), Currency.USD, Currency.BTC);
    }

    @Test
    void checkCurrencyConversion_validContextGiven_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(() -> validContext.checkCurrencyConversionValidity());
    }

    @Test
    void checkExcelLineValidity_invalidMinContextGiven_shouldNotThrowException() {
        Assertions.assertThrows(MinimumSpendAmountException.class,
                () -> invalidContextMin.checkCurrencyConversionValidity());
    }

    @Test
    void checkExcelLineValidity_invalidMaxContextGiven_shouldNotThrowException() {
        Assertions.assertThrows(MaximumSpendAmountException.class,
                () -> invalidContextMax.checkCurrencyConversionValidity());
    }

}
