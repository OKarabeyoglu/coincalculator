package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "minimum.spend.amount.error")
public class MinimumSpendAmountException extends RuntimeException {
    public MinimumSpendAmountException(String message) {
        super(message);
    }
}