package com.nicetravel.nicetravel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NiceTravelException extends RuntimeException {

    public NiceTravelException(String message) {
        super(message);
    }
}
