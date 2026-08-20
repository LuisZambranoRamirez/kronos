package com.minerva.domain.entities.sale;

import com.minerva.domain.constants.PaymentMethod;
import com.minerva.domain.valueObject.Money;
import com.minerva.domain.exceptions.DomainException;
import com.minerva.domain.exceptions.MinimumAmountException;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.exceptions.UnexpectedDomainException;
import com.minerva.domain.entities.Entity;
import com.minerva.domain.valueObject.id.PayId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

class Pay extends Entity<PayId> {
    private final Money amount;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime registrationDate;

    private static final Money MIN_AMOUNT = Money.tenCents();

    Pay(Money amount, PaymentMethod paymentMethod) throws DomainException {
        if (paymentMethod == null) throw new NullValueException("El método de pago no puede estar vacío.");
        if (amount != null && amount.isLessThan(MIN_AMOUNT)) throw new MinimumAmountException("El MONTO debe ser mayor o igual a S/" + MIN_AMOUNT);

        super(PayId.generate());
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.registrationDate = LocalDateTime.now();
    }

    Pay(UUID payId, BigDecimal amount, PaymentMethod paymentMethod, LocalDateTime registrationDate) {
        PayId payIdValueObject;
        try {
            payIdValueObject = new PayId(payId);
            this.amount = new Money(amount);
            this.paymentMethod = paymentMethod;
            this.registrationDate = registrationDate;
        } catch (DomainException e) {
            throw new UnexpectedDomainException("Error al crear el pago: " + e.getMessage(), e);
        }
        super(payIdValueObject);
    }

    public Money getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
