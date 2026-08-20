package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.InvalidDomainArgumentException;

public abstract class CustomerId extends Id<String> {
    public CustomerId(String value) throws InvalidDomainArgumentException {
        super(value);
    }
}