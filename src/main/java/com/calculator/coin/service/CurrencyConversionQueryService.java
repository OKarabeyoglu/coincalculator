package com.calculator.coin.service;

import com.calculator.coin.dto.CurrencyConversionDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyConversionQueryService {

    List<CurrencyConversionDto> getCurrencyConversionListByDate(LocalDate date);
}
