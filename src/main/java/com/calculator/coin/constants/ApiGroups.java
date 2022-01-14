package com.calculator.coin.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public final class ApiGroups {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CurrencyConversion {
        public static final String NAME = "currency-conversion-api";
        public static final String PATHS = "/api/currency-conversion/**";
    }
}
