package com.calculator.coin.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
public class CalculateExchangeRateRequest {
    @ApiModelProperty(value = "target currency", dataType = "String", example = "BTC")
    @NotBlank
    private String targetCurrency;
    @ApiModelProperty(value = "source currency", dataType = "String", example = "USD")
    @NotBlank
    private String sourceCurrency;
    @ApiModelProperty(value = "source amount", dataType = "Double", example = "5000")
    @NotNull
    private Double sourceAmount;
}