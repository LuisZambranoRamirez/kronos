package com.minerva.domain.constants;

import com.minerva.domain.entities.userAction.StringAttribute;

public enum SaleType implements StringAttribute {
    UNIDAD, GRANEL;

    @Override
    public String getAttribute() {
        return name();
    }
}
