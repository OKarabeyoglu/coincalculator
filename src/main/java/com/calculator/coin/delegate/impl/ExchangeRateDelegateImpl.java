package com.calculator.coin.delegate.impl;

import com.calculator.coin.delegate.ExchangeRateDelegate;
import com.calculator.coin.dto.ExchangeRateDto;
import com.calculator.coin.exception.NoDataException;
import com.calculator.coin.exception.ServiceCallException;
import com.calculator.coin.util.ExchangeRateServiceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateDelegateImpl implements ExchangeRateDelegate {

    private final RestTemplate restTemplate;
    private final ExchangeRateServiceProperties exchangeRateServiceProperties;

    @Override
    public Double retrieveExchangeRate(String sourceCurrency, String targetCurrency) {
        Objects.requireNonNull(sourceCurrency, "Argument ‘sourceCurrency’ can not be null!");
        Objects.requireNonNull(targetCurrency, "Argument ‘targetCurrency’ can not be null!");

        String retrieveExchangeRateUrl = exchangeRateServiceProperties.getBaseUrl() +
                targetCurrency + "-" + sourceCurrency;
        ResponseEntity<ExchangeRateDto> exchangeRateResult = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add(exchangeRateServiceProperties.getHeaderName(), exchangeRateServiceProperties.getHeaderValue());
            HttpEntity<String> entity = new HttpEntity<String>(exchangeRateServiceProperties.getHeaderBody(), headers);
            log.info("exchange service call {} {}", retrieveExchangeRateUrl, entity);
            exchangeRateResult = restTemplate.exchange(retrieveExchangeRateUrl, HttpMethod.GET, entity,
                    ExchangeRateDto.class);
        } catch (Exception ex) {
            throw new ServiceCallException("could.not.call.service", ex);
        }
        if (Objects.nonNull(exchangeRateResult.getBody())) {
            return exchangeRateResult.getBody().getLast_trade_price();
        } else {
            throw new NoDataException("no.data.found.error");
        }
    }
}
