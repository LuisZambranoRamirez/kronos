package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.valueObject.ValueObject;

public abstract class Id<I> extends ValueObject<I> {
    public Id(I value) throws NullValueException {
        super(value);
    }
}