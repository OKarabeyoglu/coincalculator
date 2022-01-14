package com.calculator.coin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRateDto {
    private String symbol;
    private Double price_24h;
    private Double volume_24h;
    private Double last_trade_price;
}
