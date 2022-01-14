package com.calculator.coin.exception;

import java.io.Serializable;

public interface BusinessValidationRule extends Serializable {

    String getCode();

    String getDescription();
}