package com.calculator.coin.request;

import com.calculator.coin.enums.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@ApiModel
public class CalculateExchangeRateRequest {
    @ApiModelProperty(value = "target currency", dataType = "Currency", example = "BTC")
    @NotNull
    private Currency targetCurrency;
    @ApiModelProperty(value = "source currency", dataType = "Currency", example = "USD")
    @NotNull
    private Currency sourceCurrency;
    @ApiModelProperty(value = "source amount", dataType = "Double", example = "5000")
    @NotNull
    @Positive
    private Double sourceAmount;
}