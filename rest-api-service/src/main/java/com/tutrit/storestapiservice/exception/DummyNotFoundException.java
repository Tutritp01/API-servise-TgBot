package com.tutrit.storestapiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.OK, reason = "Dummy not found")
public class DummyNotFoundException extends RuntimeException {
    public DummyNotFoundException(final String message) {
        super(message);
    }
}
