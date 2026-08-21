package com.minerva.domain.entities.userAction;

public record DefaultBooleanAttribute(boolean attribute) implements BooleanAttribute {
    @Override
    public Boolean getAttribute() {
        return attribute;
    }
}
