package com.harun.mancala.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyPitSowException extends RuntimeException{

    public final String errorCode = "E100003";

    public EmptyPitSowException() {
        super("Pit is empty, please choose another pit");
    }
}
