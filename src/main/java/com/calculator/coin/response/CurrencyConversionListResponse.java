package com.calculator.coin.response;

import com.calculator.coin.dto.CurrencyConversionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CurrencyConversionListResponse {
    private List<CurrencyConversionDto> currencyConversionDtoList;
}

