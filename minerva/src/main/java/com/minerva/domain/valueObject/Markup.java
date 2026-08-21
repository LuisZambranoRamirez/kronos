package com.minerva.domain.valueObject;

import com.minerva.domain.constants.GainStrategy;
import com.minerva.domain.exceptions.InvalidDomainArgumentException;
import com.minerva.domain.exceptions.NullValueException;
import com.minerva.domain.services.Math;

import java.math.BigDecimal;

public class Markup extends ValueObject<BigDecimal> {
    private final GainStrategy gainStrategy;

    public Markup(BigDecimal value, GainStrategy gainStrategy) throws InvalidDomainArgumentException {
        super(value);
        if (gainStrategy == null) throw new NullValueException("Seleccione una estrategia de ganancia.");
        if (Math.isZeroOrLess(value)) throw new InvalidDomainArgumentException("El valor de la ganancia debe ser mayor que cero.");
        this.gainStrategy = gainStrategy;
    }

    public Money apply(Money cost) throws InvalidDomainArgumentException {
        return switch (gainStrategy) {
            case RECARGO_FIJO -> cost.add(new Money(getValue()));
            case PORCENTAJE -> cost.incrementPercentage(getValue());
        };
    }

    public GainStrategy getGainStrategy() {
        return gainStrategy;
    }
}
