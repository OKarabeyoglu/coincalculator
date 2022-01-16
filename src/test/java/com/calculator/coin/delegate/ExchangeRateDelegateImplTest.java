package com.calculator.coin.delegate;

import com.calculator.coin.delegate.impl.ExchangeRateDelegateImpl;
import com.calculator.coin.dto.ExchangeRateDto;
import com.calculator.coin.exception.NoDataException;
import com.calculator.coin.exception.ServiceCallException;
import com.calculator.coin.util.ExchangeRateServiceProperties;
import org.junit.jupiter.api.Assertions;
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
class ExchangeRateDelegateImplTest {
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
        exchangeRateDto.setVolume_24h(Double.valueOf(45000));
        exchangeRateDto.setPrice_24h(Double.valueOf(45000));
        responseEntity = new ResponseEntity(exchangeRateDto, HttpStatus.OK);
    }

    @Test
    void retrieveExchangeRate_validBaseCurrencyAndTargetCurrencyGiven_shouldReturnCurrencyPairRate() {
        when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class))).thenReturn(responseEntity);
        when(exchangeRateServiceProperties.getHeaderName()).thenReturn("user-agent");

        Double rate = exchangeRateDelegate.retrieveExchangeRate(sourceCurrency, targetCurrency);

        verify(this.restTemplate).exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class));

        assertThat(rate).isNotNull().isEqualTo(Double.valueOf(45000));
        assertThat(responseEntity.getBody().getLast_trade_price()).isEqualTo(exchangeRateDto.getLast_trade_price());
        assertThat(responseEntity.getBody().getSymbol()).isEqualTo(exchangeRateDto.getSymbol());
        assertThat(responseEntity.getBody().getVolume_24h()).isEqualTo(exchangeRateDto.getVolume_24h());
        assertThat(responseEntity.getBody().getPrice_24h()).isEqualTo(exchangeRateDto.getPrice_24h());
    }

    @Test
    void retrieveExchangeRate_validBaseCurrencyAndTargetCurrencyGivenWithInvalidRestTemplate_shouldThrowServiceCallError() {
        when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class))).thenThrow(RuntimeException.class);
        when(exchangeRateServiceProperties.getHeaderName()).thenReturn("user-agent");

        Assertions.assertThrows(ServiceCallException.class,
                () -> exchangeRateDelegate.retrieveExchangeRate(sourceCurrency, targetCurrency));
    }

    @Test
    void retrieveExchangeRate_validBaseCurrencyAndTargetCurrencyGivenServiceNotReturnData_shouldThrowNoDataError() {
        responseEntity = new ResponseEntity(null, HttpStatus.OK);
        when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class))).thenReturn(responseEntity);
        when(exchangeRateServiceProperties.getHeaderName()).thenReturn("user-agent");

        Assertions.assertThrows(NoDataException.class,
                () -> exchangeRateDelegate.retrieveExchangeRate(sourceCurrency, targetCurrency));
    }
}
