package com.calculator.coin.enums;

import com.calculator.coin.exception.BusinessValidationRule;
import lombok.Getter;

@Getter
public enum CurrencyValidationRule implements BusinessValidationRule {

    SERVICE_CALL_ERROR("SERVICE_CALL_ERROR", "An error occurred while calling data fixer service"),
    NO_DATA_ERROR("NO_DATA_ERROR", "An error occurred while retrieving data exchange rate"),
    MINIMUM_SPEND_AMOUNT_ERROR("MINIMUM_SPEND_AMOUNT_ERROR","Minimum spend amount is 25 USD/EUR"),
    MAXIMUM_SPEND_AMOUNT_ERROR("MAXIMUM_SPEND_AMOUNT_ERROR","Maximum spend amount 5000 USD/EUR");

    private final String code;
    private final String description;

    CurrencyValidationRule(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
