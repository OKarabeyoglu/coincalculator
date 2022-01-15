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
    CurrencyConversionContext invalidContextMinSourceAmount;
    CurrencyConversionContext invalidContextMaxSourceAmount;
    CurrencyConversionContext invalidContextSourceCurrency;
    CurrencyConversionContext invalidContextTargetCurrency;


    @BeforeEach
    void setup() {
        validContext = CurrencyConversionContext.of(Double.valueOf(1000), Currency.USD, Currency.BTC);
        invalidContextMinSourceAmount = CurrencyConversionContext.of(Double.valueOf(10), Currency.USD, Currency.BTC);
        invalidContextMaxSourceAmount = CurrencyConversionContext.of(Double.valueOf(10000), Currency.USD, Currency.BTC);
        invalidContextSourceCurrency = CurrencyConversionContext.of(Double.valueOf(1000), Currency.ETH, Currency.BTC);
        invalidContextTargetCurrency = CurrencyConversionContext.of(Double.valueOf(1000), Currency.USD, Currency.EUR);
    }

    @Test
    void checkCurrencyConversion_validContextGiven_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(() -> validContext.checkCurrencyConversionValidity());
    }

    @Test
    void checkConversionValidity_invalidMinContextGiven_shouldNotThrowException() {
        Assertions.assertThrows(MinimumSpendAmountException.class,
                () -> invalidContextMinSourceAmount.checkCurrencyConversionValidity());
    }

    @Test
    void checkConversionValidity_invalidMaxContextGiven_shouldNotThrowException() {
        Assertions.assertThrows(MaximumSpendAmountException.class,
                () -> invalidContextMaxSourceAmount.checkCurrencyConversionValidity());
    }

    @Test
    void checkConversionValidity_invalidSourceCurrencyGiven_shouldNotThrowException() {
        Assertions.assertThrows(SourceCurrencyException.class,
                () -> invalidContextSourceCurrency.checkCurrencyConversionValidity());
    }

    @Test
    void checkConversionValidity_invalidTargetCurrencyGiven_shouldNotThrowException() {
        Assertions.assertThrows(TargetCurrencyException.class,
                () -> invalidContextTargetCurrency.checkCurrencyConversionValidity());
    }

}
