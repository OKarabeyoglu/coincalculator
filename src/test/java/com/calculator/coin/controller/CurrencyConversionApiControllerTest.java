package com.calculator.coin.controller;

import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.enums.Currency;
import com.calculator.coin.request.CalculateExchangeRateRequest;
import com.calculator.coin.response.CalculateExchangeRateResponse;
import com.calculator.coin.response.CurrencyConversionListResponse;
import com.calculator.coin.service.CurrencyConversionCommandService;
import com.calculator.coin.service.CurrencyConversionQueryService;
import com.calculator.coin.util.CurrencyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionApiControllerTest<CurrencyConversionPageDto> {

    @Mock
    private CurrencyConversionQueryService currencyConversionQueryService;
    @Mock
    private CurrencyConversionCommandService currencyConversionCommandService;

    @InjectMocks
    private CurrencyConversionApiController currencyConversionApiController;

    private LocalDate transactionDate;
    private String targetCurrency;
    private String sourceCurrency;
    private Double sourceAmount;
    private CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto();
    private List<CurrencyConversionDto> currencyConversionDtoList = new ArrayList<>();
    private CalculateExchangeRateRequest calculateExchangeRateRequest = new CalculateExchangeRateRequest();
    private CurrencyConversionListResponse currencyConversionListResponse = new CurrencyConversionListResponse();

    @BeforeEach
    void setup() {
        sourceCurrency = "USD";
        targetCurrency = "BTC";
        sourceAmount = Double.valueOf(1000);
        transactionDate = LocalDate.now();
        currencyConversionDto.setId(1L);
        currencyConversionDto.setTargetCurrency(targetCurrency);
        currencyConversionDto.setSourceCurrency(sourceCurrency);
        currencyConversionDto.setSourceAmount(sourceAmount);
        currencyConversionDto.setTransactionDate(OffsetDateTime.now());
        currencyConversionDto.setTransactionIdxNo(CurrencyUtil.createTransactionIdxNo(currencyConversionDto.getTransactionDate()));
        currencyConversionDto.setConvertedAmount(Double.valueOf(10000));
        currencyConversionDtoList.add(currencyConversionDto);
        calculateExchangeRateRequest.setTargetCurrency(Currency.BTC);
        calculateExchangeRateRequest.setSourceCurrency(Currency.USD);
        calculateExchangeRateRequest.setSourceAmount(Double.valueOf(1000));
        currencyConversionListResponse.setCurrencyConversionDtoList(currencyConversionDtoList);
    }

    @Test
    void getCurrencyConversionList_validTransactionIdGiven_shouldReturnCurrencyConversionListResponse() {

        when(currencyConversionQueryService.getCurrencyConversionListByDate(transactionDate))
                .thenReturn(currencyConversionDtoList);

        currencyConversionListResponse = currencyConversionApiController.getCurrencyConversionListByDate(transactionDate);

        verify(this.currencyConversionQueryService).getCurrencyConversionListByDate(transactionDate);

        assertThat(currencyConversionListResponse).isNotNull();
        assertThat(currencyConversionListResponse.getCurrencyConversionDtoList()).isNotNull();
    }

    @Test
    void calculateExchangeRate_validCalculateExchangeRateRequestGiven_shouldReturnCurrencyConversionDto() {
        //stubbing
        when(currencyConversionCommandService.createCurrencyConversionTransaction(Currency.USD, Currency.BTC, sourceAmount))
                .thenReturn(currencyConversionDto);
        //interaction
        CalculateExchangeRateResponse response = currencyConversionApiController.calculateExchangeRate(calculateExchangeRateRequest);
        //verification
        verify(this.currencyConversionCommandService).createCurrencyConversionTransaction(Currency.USD, Currency.BTC, sourceAmount);
        //assertion
        assertThat(response).isNotNull();
        assertThat(response.getCurrencyConversionDto()).isNotNull();
        assertThat(response.getCurrencyConversionDto().getSourceCurrency()).isEqualTo(sourceCurrency);
    }

}
