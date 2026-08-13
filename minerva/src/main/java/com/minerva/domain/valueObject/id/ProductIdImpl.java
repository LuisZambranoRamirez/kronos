package com.minerva.domain.valueObject.id;

import com.minerva.domain.entities.product.ProductId;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.valueObject.ValueObject;

import java.util.UUID;

public class ProductIdImpl extends ValueObject<UUID>  implements ProductId {

    // Seguir este ejemplo para todos
    public ProductIdImpl(UUID value) throws NullValueException {
        if (value == null) throw new NullValueException("El productId no puede ser nulo");
        super(value);
    }

    public static ProductIdImpl generate() {
        try {
            return new ProductIdImpl(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de product: " + e.getMessage(), e);
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
