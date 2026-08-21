package com.minerva.domain.constants;

import com.minerva.domain.entities.userAction.StringAttribute;

public enum ReasonProductReturn implements StringAttribute {
    DAÑADO, VENCIDO, EQUIVOCACION, OTROS;

    @Override
    public String getAttribute() {
        return name();
    }
}
