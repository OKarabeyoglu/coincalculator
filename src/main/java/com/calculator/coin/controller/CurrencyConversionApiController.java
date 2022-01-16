package com.calculator.coin.controller;

import com.calculator.coin.constants.ApiEndpoints;
import com.calculator.coin.constants.ApiGroups;
import com.calculator.coin.request.CalculateExchangeRateRequest;
import com.calculator.coin.response.CalculateExchangeRateResponse;
import com.calculator.coin.response.CurrencyConversionListResponse;
import com.calculator.coin.service.CurrencyConversionCommandService;
import com.calculator.coin.service.CurrencyConversionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequestMapping(value = ApiEndpoints.CURRENCY_CONVERSION_API)
@Api(value = ApiGroups.CurrencyConversion.NAME)
@RequiredArgsConstructor
public class CurrencyConversionApiController {

    private final CurrencyConversionQueryService currencyConversionQueryService;
    private final CurrencyConversionCommandService currencyConversionCommandService;

    @GetMapping(value = "")
    @ApiOperation(value = "Search currency conversions by date", notes = "This method get currency conversion list by " +
            "transaction date")
    public CurrencyConversionListResponse getCurrencyConversionListByDate(
            @RequestParam(name = "transactionDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
        return new CurrencyConversionListResponse(currencyConversionQueryService.getCurrencyConversionListByDate(transactionDate));
    }

    @PostMapping(value = "create")
    @ApiOperation(value = "Create and return currency conversion transaction", notes = "This method create currency " +
            "conversion transaction.")
    public CalculateExchangeRateResponse calculateExchangeRate(@RequestBody @Valid @NotNull(
            message = "{calculateExchangeRateRequest.can.not.be.null}") CalculateExchangeRateRequest request) {
        return new CalculateExchangeRateResponse(currencyConversionCommandService
                .createCurrencyConversionTransaction(request.getSourceCurrency(), request.getTargetCurrency(), request.getSourceAmount()));
    }
}
