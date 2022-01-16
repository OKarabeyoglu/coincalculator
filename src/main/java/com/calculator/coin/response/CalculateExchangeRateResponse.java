package com.calculator.coin.response;

import com.calculator.coin.dto.CurrencyConversionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateExchangeRateResponse {
    private CurrencyConversionDto currencyConversionDto;
}

