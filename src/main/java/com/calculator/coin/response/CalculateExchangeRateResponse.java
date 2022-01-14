package com.calculator.coin.response;

import com.calculator.coin.dto.CurrencyConversionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateExchangeRateResponse {
    private CurrencyConversionDto currencyConversionDto;
}

