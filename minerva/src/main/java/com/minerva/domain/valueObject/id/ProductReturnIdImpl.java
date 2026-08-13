package com.minerva.domain.valueObject.id;

import java.util.UUID;

import com.minerva.domain.entities.sale.ProductReturnId;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.valueObject.ValueObject;

public class ProductReturnIdImpl extends ValueObject<UUID> implements ProductReturnId {

    public ProductReturnIdImpl(UUID value) throws NullValueException {
        super(value);
    }

    public static ProductReturnIdImpl generate() {
        try {
            return new ProductReturnIdImpl(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de devolución de producto: " + e.getMessage(), e);
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
