package com.minerva.domain.valueObject.id;

import java.util.UUID;

import com.minerva.domain.entities.sale.SaleId;
import com.minerva.domain.valueObject.ValueObject;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;

public class SaleIdImpl extends ValueObject<UUID> implements SaleId {
    public SaleIdImpl(UUID value) throws NullValueException {
        super(value);
    }

    public static SaleIdImpl generate() {
        try {
            return new SaleIdImpl(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de venta: " + e.getMessage(), e);
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
