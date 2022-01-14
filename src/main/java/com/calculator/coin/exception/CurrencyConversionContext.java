package com.calculator.coin.exception;

import com.calculator.coin.enums.CurrencyValidationRule;
import lombok.Value;

@Value(staticConstructor = "of")
public class CurrencyConversionContext {
    Double sourceAmount;

    private static final Double MIN_SPEND_AMOUNT = Double.valueOf(25);
    private static final Double MAX_SPEND_AMOUNT = Double.valueOf(5000);

    public void checkCurrencyConversionValidity() {
        if (sourceAmount < MIN_SPEND_AMOUNT) {
            throw new BusinessValidationException(CurrencyValidationRule.MINIMUM_SPEND_AMOUNT_ERROR);
        }
        if (sourceAmount > MAX_SPEND_AMOUNT) {
            throw new BusinessValidationException(CurrencyValidationRule.MAXIMUM_SPEND_AMOUNT_ERROR);
        }
    }
}
