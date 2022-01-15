package com.calculator.coin.exception;

import com.calculator.coin.enums.Currency;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Value(staticConstructor = "of")
@Slf4j
public class CurrencyConversionContext {
    Double sourceAmount;
    Currency sourceCurrency;
    Currency targetCurrency;

    private static final Double MIN_SPEND_AMOUNT = Double.valueOf(25);
    private static final Double MAX_SPEND_AMOUNT = Double.valueOf(5000);

    public void checkCurrencyConversionValidity() {
        if (!Currency.sourceCurrencies().contains(sourceCurrency)) {
            log.error("source.currency.not.valid sourceCurrency: {}", sourceCurrency);
            throw new SourceCurrencyException("source.currency.not.valid");
        }
        if (!Currency.targetCurrencies().contains(targetCurrency)) {
            log.error("target.currency.not.valid targetCurrency: {}", targetCurrency);
            throw new TargetCurrencyException("target.currency.not.valid");
        }
        if (sourceAmount < MIN_SPEND_AMOUNT) {
            log.error("minimum.spend.amount.error sourceAmount: {}", sourceAmount);
            throw new MinimumSpendAmountException("minimum.spend.amount.error");
        }
        if (sourceAmount > MAX_SPEND_AMOUNT) {
            log.error("maximum.spend.amount.error sourceAmount: {}", sourceAmount);
            throw new MaximumSpendAmountException("maximum.spend.amount.error");
        }
    }
}
