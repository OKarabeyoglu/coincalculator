package com.calculator.coin.delegate;

import com.calculator.coin.delegate.impl.ExchangeRateDelegateImpl;
import com.calculator.coin.dto.ExchangeRateDto;
import com.calculator.coin.util.ExchangeRateServiceProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateDelegateImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ExchangeRateServiceProperties exchangeRateServiceProperties;
    @InjectMocks
    private ExchangeRateDelegateImpl exchangeRateDelegate;

    private String sourceCurrency;
    private String targetCurrency;
    private ExchangeRateDto exchangeRateDto;
    private ResponseEntity<ExchangeRateDto> responseEntity;

    @BeforeEach
    void setup() {
        sourceCurrency = "USD";
        targetCurrency = "BTC";
        exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setSymbol("BTC-USD");
        exchangeRateDto.setLast_trade_price(Double.valueOf(45000));
        responseEntity = new ResponseEntity(exchangeRateDto, HttpStatus.OK);
    }

    @Test
    void retrieveExchangeRate_validBaseCurrencyAndTargetCurrencyGivenShouldReturnCurrencyPairRate(){
        when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class))).thenReturn(responseEntity);
        when(exchangeRateServiceProperties.getHeaderName()).thenReturn("user-agent");

        Double rate = exchangeRateDelegate.retrieveExchangeRate(sourceCurrency, targetCurrency);

        verify(this.restTemplate).exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class));

        assertThat(rate).isNotNull();
        assertThat(rate).isEqualTo(Double.valueOf(10));
    }
}
