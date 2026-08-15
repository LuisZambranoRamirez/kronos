package com.minerva.domain.entities.product;

import com.minerva.domain.constants.ReasonProductLoss;
import com.minerva.domain.exceptions.DomainException;
import com.minerva.domain.exceptions.MinimumAmountException;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.entities.Entity;
import com.minerva.domain.valueObject.Observation;
import com.minerva.domain.valueObject.ProductQuantity;
import com.minerva.domain.valueObject.id.InventoryLossIdImpl;

import java.time.LocalDateTime;
import java.util.Optional;

public class InventoryLoss extends Entity<InventoryLossId> {

    private final ProductId productId;
    private final ProductQuantity quantity;
    private ReasonProductLoss reason;
    private Observation observation;
    private final LocalDateTime registrationDate;

    public InventoryLoss(
            ProductId productId,
            ProductQuantity quantity,
            ReasonProductLoss reason,
            String observation
    ) throws DomainException {

        if (productId == null) throw new NullValueException("El nombre del producto no puede estar vacío.");
        if (quantity == null) throw new NullValueException("La cantidad debe ser mayor a cero.");
        if (quantity.isZeroOrLess()) throw new MinimumAmountException("La cantidad debe ser mayor a cero.");
        if (reason == null) throw new NullValueException("Debe especificar la razón de la pérdida.");

        super(InventoryLossIdImpl.generate());

        this.productId = productId;
        this.quantity = quantity;
        this.reason = reason;
        this.observation = observation == null
                ? null
                : new Observation(observation);
        this.registrationDate = LocalDateTime.now();
    }

    public ProductId getProductId() {
        return productId;
    }

    public ProductQuantity getQuantity() {
        return quantity;
    }

    public Optional<Observation> getObservation() {
        return Optional.ofNullable(observation);
    }

    public ReasonProductLoss getReason() {
        return reason;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

}
