package com.calculator.coin.service;

import com.calculator.coin.delegate.ExchangeRateDelegate;
import com.calculator.coin.domain.CurrencyConversion;
import com.calculator.coin.dto.CurrencyConversionDto;
import com.calculator.coin.repository.CurrencyConversionRepository;
import com.calculator.coin.service.impl.CurrencyConversionCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CurrencyConversionCommandServiceImplTest {

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;
    @Mock
    private ExchangeRateDelegate exchangeRateDelegate;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CurrencyConversionCommandServiceImpl currencyConversionCommandService;

    private String sourceCurrency;
    private String targetCurrency;
    private Double sourceAmount;
    private CurrencyConversion currencyConversion;
    private CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto();

    @BeforeEach
    void setup() {
        sourceCurrency = "USD";
        targetCurrency = "BTC";
        sourceAmount = Double.valueOf(45000);
        currencyConversion = CurrencyConversion.builder()
                .id(1L)
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .sourceAmount(sourceAmount)
                .rate(Double.valueOf(10)).build();
        currencyConversionDto.setId(1L);
        currencyConversionDto.setSourceCurrency(sourceCurrency);
        currencyConversionDto.setTargetCurrency(targetCurrency);
        currencyConversionDto.setSourceAmount(sourceAmount);
        currencyConversionDto.setRate(Double.valueOf(45000));
    }

    @Test
    void convertCurrency_invalidFromCurrencyAndToCurrencyAndSourceAmountGiven_shouldReturnNullCurrencyConversionDto() {
        when(exchangeRateDelegate.retrieveExchangeRate(sourceCurrency, targetCurrency)).thenReturn(Double.valueOf(45000));
        when(currencyConversionRepository.save(Mockito.any())).thenReturn(currencyConversion);
        when(modelMapper.map(Mockito.eq(currencyConversion), Mockito.eq(CurrencyConversionDto.class))).thenReturn(currencyConversionDto);

        CurrencyConversionDto currencyConversionDto = currencyConversionCommandService.
                createCurrencyConversionTransaction(sourceCurrency, targetCurrency, sourceAmount);

        verify(exchangeRateDelegate).retrieveExchangeRate(sourceCurrency, targetCurrency);
        verify(currencyConversionRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.eq(currencyConversion), Mockito.eq(CurrencyConversionDto.class));

        assertThat(currencyConversionDto).isNotNull();
        assertThat(currencyConversionDto.getSourceCurrency()).isEqualTo(sourceCurrency);
        assertThat(currencyConversionDto.getTargetCurrency()).isEqualTo(targetCurrency);
        assertThat(currencyConversionDto.getSourceAmount()).isEqualTo(sourceAmount);
    }
}
