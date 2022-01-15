package com.calculator.coin.service.impl;

import com.calculator.coin.domain.CurrencyConversion;
import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.repository.CurrencyConversionRepository;
import com.calculator.coin.service.CurrencyConversionQueryService;
import com.calculator.coin.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyConversionQueryServiceImpl implements CurrencyConversionQueryService {

    private final CurrencyConversionRepository currencyConversionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CurrencyConversionDto> getCurrencyConversionListByDate(LocalDate date) {
        Objects.requireNonNull(date, "Argument ‘date’ can not be null!");

        List<CurrencyConversionDto> currencyConversionDtoList = new ArrayList<>();
        List<CurrencyConversion> currencyConversionList = currencyConversionRepository.
                findByTransactionIdxNo(CurrencyUtil.createTransactionIdxNo(date));
        if (!CollectionUtils.isEmpty(currencyConversionList)) {
            currencyConversionDtoList = currencyConversionList.stream().map(c -> modelMapper.map(c, CurrencyConversionDto.class))
                    .collect(Collectors.toList());
        }
        return currencyConversionDtoList;
    }
}
