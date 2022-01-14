package com.calculator.coin.service;

import com.calculator.coin.domain.CurrencyConversion;
import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.repository.CurrencyConversionRepository;
import com.calculator.coin.service.impl.CurrencyConversionQueryServiceImpl;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionQueryServiceImplTest {

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CurrencyConversionQueryServiceImpl currencyConversionQueryService;

    private List<CurrencyConversion> currencyConversionList;


    @BeforeEach
    void setup() {
        List<CurrencyConversion> currencyConversionList = new ArrayList<>();
        currencyConversionList.add(CurrencyConversion.builder().id(1L).
                sourceCurrency("USD").
                targetCurrency("BTC").
                sourceAmount(Double.valueOf(45000)).
                rate(Double.valueOf(1)).build());
    }

    @Test
    void getCurrencyConversionList_validFromTransactionIdOrTransactionDateGiven_shouldReturnCurrencyConversionListDto() {
        when(currencyConversionRepository.findByTransactionIdxNo(Mockito.any())).thenReturn(currencyConversionList);

        List<CurrencyConversionDto> currencyConversionDtoList = currencyConversionQueryService
                .getCurrencyConversionListByDate(LocalDate.now());

        verify(currencyConversionRepository).findByTransactionIdxNo(Mockito.any());

        assertThat(currencyConversionDtoList).isNotNull();
    }
}
