package com.minerva.domain.constants;

import com.minerva.domain.entities.userAction.StringAttribute;

public enum GainStrategy implements StringAttribute {
    PORCENTAJE, RECARGO_FIJO;

    @Override
    public String getAttribute() {
        return name();
    }
}
