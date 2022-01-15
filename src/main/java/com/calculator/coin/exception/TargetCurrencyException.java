package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "target.currency.not.valid")
public class TargetCurrencyException extends RuntimeException {
    public TargetCurrencyException(String message) {
        super(message);
    }
}