package com.minerva.domain.entities.userAction;

import java.math.BigDecimal;

public record DefaultNumericAttribute(BigDecimal value) implements NumericAttribute {
    @Override
    public BigDecimal getAttribute() {
        return value;
    }
}