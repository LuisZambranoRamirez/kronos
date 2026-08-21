package com.minerva.domain.entities.userAction;

import java.math.BigDecimal;

public record DefaultNumericAttribute(BigDecimal attribute) implements NumericAttribute {
    @Override
    public BigDecimal getAttribute() {
        return attribute;
    }
}