package com.calculator.coin.delegate;

public interface ExchangeRateDelegate {

    Double retrieveExchangeRate(String sourceCurrency, String targetCurrency);
}
