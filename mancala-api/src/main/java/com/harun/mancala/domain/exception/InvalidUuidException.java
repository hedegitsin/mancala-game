package com.harun.mancala.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidUuidException extends RuntimeException {

    public final String errorCode = "E100001";

    public InvalidUuidException() {
        super("Invalid UUID");
    }

    public InvalidUuidException(final String message) {
        super(message);
    }
}
