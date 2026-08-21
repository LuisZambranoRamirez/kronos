package com.minerva.domain.valueObject;

import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.entities.userAction.StringAttribute;

public class PasswordHash extends ValueObject<String> implements StringAttribute {
    public PasswordHash(String value) throws NullValueException {
        super(value);
    }

    @Override
    public String getAttribute() {
        return getValue();
    }
}
