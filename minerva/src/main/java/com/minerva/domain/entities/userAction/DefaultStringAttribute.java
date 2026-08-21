package com.minerva.domain.entities.userAction;

public record DefaultStringAttribute(String value) implements StringAttribute {
    @Override
    public String getAttribute() {
        return value;
    }
}