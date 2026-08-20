package com.minerva.domain.valueObject;

import com.minerva.domain.exceptions.InvalidDomainArgumentException;
import com.minerva.domain.services.Name;
import com.minerva.domain.services.Result;

public class FullName extends ValueObject<String> {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;

    public FullName(String value) throws InvalidDomainArgumentException {
        super(value);
        Result<Void> result = Name.validateFormat(value, MIN_LENGTH, MAX_LENGTH);
        if (result.isFail()) throw new InvalidDomainArgumentException(result.getMessage());
    }

    @Override
    public String toString() {
        return value;
    }
}
