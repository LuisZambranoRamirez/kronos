package com.minerva.domain.exceptions;

public class InvalidDomainArgumentException extends DomainException {

    public InvalidDomainArgumentException(String message) {
        super(message);
    }

    public InvalidDomainArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
