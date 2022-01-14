package com.calculator.coin.exception;

import lombok.Value;

@Value(staticConstructor = "of")
public class CurrencyConversionContext {
    Double sourceAmount;

    private static final Double MIN_SPEND_AMOUNT = Double.valueOf(25);
    private static final Double MAX_SPEND_AMOUNT = Double.valueOf(5000);

    public void checkCurrencyConversionValidity() {
        if (sourceAmount < MIN_SPEND_AMOUNT) {
            throw new MinimumSpendAmountException("minimum.spend.amount.error");
        }
        if (sourceAmount > MAX_SPEND_AMOUNT) {
            throw new MaximumSpendAmountException("maximum.spend.amount.error");
        }
    }
}
