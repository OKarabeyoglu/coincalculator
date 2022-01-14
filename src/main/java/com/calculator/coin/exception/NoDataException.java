package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no.data.found.error")
public class NoDataException extends RuntimeException {
    public NoDataException(String message) {
        super(message);
    }
}