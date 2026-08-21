package com.minerva.domain.constants;

import com.minerva.domain.entities.userAction.StringAttribute;

public enum PaymentMethod implements StringAttribute {
    EFECTIVO, DIGITAL;

    @Override
    public String getAttribute() {
        return name();
    }
}