package com.harun.mancala.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {

    public final String errorCode = "E100003";

    public EntityNotFoundException() {
        super("Entity not found");
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
