package com.tutrit.storestapiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundExceptionResponseStatus extends ResponseStatusException {

    public EntityNotFoundExceptionResponseStatus() {
        super(HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundExceptionResponseStatus(
            HttpStatusCode status, @Nullable String reason, @Nullable Throwable cause,
            @Nullable String messageDetailCode, @Nullable Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
