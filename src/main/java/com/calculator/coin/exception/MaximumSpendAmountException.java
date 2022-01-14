package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "maximum.spend.amount.error")
public class MaximumSpendAmountException extends RuntimeException {
    public MaximumSpendAmountException(String message) {
        super(message);
    }
}