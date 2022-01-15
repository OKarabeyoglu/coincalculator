package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "source.currency.not.valid")
public class SourceCurrencyException extends RuntimeException {
    public SourceCurrencyException(String message) {
        super(message);
    }
}
