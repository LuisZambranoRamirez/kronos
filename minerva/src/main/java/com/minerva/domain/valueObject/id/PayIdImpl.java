package com.minerva.domain.valueObject.id;

import java.util.UUID;

import com.minerva.domain.entities.sale.PayId;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.valueObject.ValueObject;

public class PayIdImpl extends ValueObject<UUID> implements PayId {

    public PayIdImpl(UUID value) throws NullValueException {
        super(value);
    }

    public static PayIdImpl generate() {
        try {
            return new PayIdImpl(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de pago: " + e.getMessage(), e);
        }
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public UUID value() {
        return value;
    }
    
}
