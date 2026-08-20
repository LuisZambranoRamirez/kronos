package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;

import java.util.UUID;

public class PayId extends Id<UUID> {
    public PayId(UUID value) throws NullValueException {
        super(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static PayId generate() {
        try {
            return new PayId(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de pago: " + e.getMessage(), e);
        }
    }

}
