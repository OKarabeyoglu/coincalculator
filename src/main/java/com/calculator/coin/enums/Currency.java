package com.calculator.coin.enums;

import java.util.Arrays;
import java.util.List;

public enum Currency {
    USD, EUR, BTC, ETH;

    public static List<Currency> sourceCurrencies() { return Arrays.asList(USD, EUR); }

    public static List<Currency> targetCurrencies() { return Arrays.asList(BTC, ETH); }
}
