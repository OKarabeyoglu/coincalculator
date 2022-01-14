package com.calculator.coin.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "blockchainservice")
public class ExchangeRateServiceProperties {
    private String baseUrl;
    private String accessKey;
    private String headerName;
    private String headerValue;
    private String headerBody;
}
