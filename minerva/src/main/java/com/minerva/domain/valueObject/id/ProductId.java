package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;

import java.util.UUID;

public class ProductId extends Id<UUID> {

    public ProductId(UUID value) throws NullValueException {
        super(value);
    }

    public static ProductId generate() {
        try {
            return new ProductId(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de product: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
