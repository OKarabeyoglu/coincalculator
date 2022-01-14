package com.calculator.coin.service;

import com.calculator.coin.dto.CurrencyConversionDto;

public interface CurrencyConversionCommandService {

    CurrencyConversionDto createCurrencyConversionTransaction(String sourceCurrency, String targetCurrency,
                                                              Double sourceAmount);
}
