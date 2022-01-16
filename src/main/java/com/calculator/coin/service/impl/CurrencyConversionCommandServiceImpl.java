package com.calculator.coin.service.impl;

import com.calculator.coin.delegate.ExchangeRateDelegate;
import com.calculator.coin.domain.CurrencyConversion;
import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.enums.Currency;
import com.calculator.coin.exception.CurrencyConversionContext;
import com.calculator.coin.repository.CurrencyConversionRepository;
import com.calculator.coin.service.CurrencyConversionCommandService;
import com.calculator.coin.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CurrencyConversionCommandServiceImpl implements CurrencyConversionCommandService {

    private final ExchangeRateDelegate exchangeRateDelegate;
    private final CurrencyConversionRepository currencyConversionRepository;
    private final ModelMapper modelMapper;

    @Override
    public CurrencyConversionDto createCurrencyConversionTransaction(Currency sourceCurrency,
                                                                     Currency targetCurrency, Double sourceAmount) {
        Objects.requireNonNull(sourceCurrency, "Argument ‘sourceCurrency’ can not be null!");
        Objects.requireNonNull(targetCurrency, "Argument ‘targetCurrency’ can not be null!");
        Objects.requireNonNull(sourceAmount, "Argument ‘sourceAmount’ can not be null!");

        CurrencyConversionContext.of(sourceAmount, sourceCurrency, targetCurrency).checkCurrencyConversionValidity();
        Double rate = exchangeRateDelegate.retrieveExchangeRate(sourceCurrency.name(), targetCurrency.name());
        return saveCurrencyConversionTransaction(sourceCurrency.name(), targetCurrency.name(), sourceAmount, rate);
    }

    private CurrencyConversionDto saveCurrencyConversionTransaction(String sourceCurrency, String targetCurrency,
                                                                    Double amount, Double rate) {
        CurrencyConversion currencyConversion = CurrencyConversion.builder()
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .rate(rate)
                .sourceAmount(amount)
                .convertedAmount(amount / rate)
                .transactionDate(OffsetDateTime.now())
                .transactionIdxNo(CurrencyUtil.createTransactionIdxNo(OffsetDateTime.now()))
                .build();
        return modelMapper.map(currencyConversionRepository.save(currencyConversion),
                CurrencyConversionDto.class);
    }
}
