package com.minerva.domain.exceptions;

public final class NullValueException extends InvalidDomainArgumentException {

    public NullValueException(String message) {
        super(message);
    }

    public NullValueException(String message, Throwable e) {
        super(message, e);
    }
}
