package com.minerva.domain.valueObject.id;

import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;

import java.util.UUID;

public class InventoryLossId extends Id<UUID> {
    public InventoryLossId(UUID value) throws NullValueException {
        super(value);
    }

    public static InventoryLossId generate() {
        try {
            return new InventoryLossId(UUID.randomUUID());
        } catch (NullValueException e) {
            throw new UnexpectedDomainException("Error al generar el ID de pérdida de inventario: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
