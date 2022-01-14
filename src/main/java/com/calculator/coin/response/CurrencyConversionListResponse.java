package com.calculator.coin.response;

import com.calculator.coin.dto.CurrencyConversionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyConversionListResponse {
    private List<CurrencyConversionDto> currencyConversionDtoList;
}

