package com.minerva.domain.valueObject.id;

import java.util.UUID;

import com.minerva.domain.entities.product.InventoryLossId;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.valueObject.ValueObject;

public class InventoryLossIdImpl extends ValueObject<UUID> implements InventoryLossId {

    private InventoryLossIdImpl(UUID value) throws NullValueException {
        super(value);
    }

    public static InventoryLossIdImpl generate() {
        try {
            return new InventoryLossIdImpl(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de pérdida de inventario: " + e.getMessage(), e);
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
