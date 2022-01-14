package com.calculator.coin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "could.not.call.service")
public class ServiceCallException extends RuntimeException {
    private final String causeDetailMessage;
    public ServiceCallException(String message, Throwable cause) {
        super(message);
        this.causeDetailMessage = cause.getMessage();
    }
}