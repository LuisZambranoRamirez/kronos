package com.minerva.domain.entities.userAction;

public record DefaultStringAttribute(String attribute) implements StringAttribute {
    @Override
    public String getAttribute() {
        return attribute;
    }
}