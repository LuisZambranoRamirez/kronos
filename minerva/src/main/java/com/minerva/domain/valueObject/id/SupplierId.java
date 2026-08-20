package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.NullValueException;

public class SupplierId extends Id<String> {

    public SupplierId(String value) throws NullValueException {
        super(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
