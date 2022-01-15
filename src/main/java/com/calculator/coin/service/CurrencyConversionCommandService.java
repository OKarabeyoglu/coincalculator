package com.calculator.coin.service;

import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.enums.Currency;

public interface CurrencyConversionCommandService {

    CurrencyConversionDto createCurrencyConversionTransaction(Currency sourceCurrency, Currency targetCurrency,
                                                              Double sourceAmount);
}
