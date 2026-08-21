package com.minerva.domain.entities.userAction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DefaultDateTimeAttribute(LocalDateTime attribute) implements StringAttribute {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String getAttribute() {
        return attribute == null
                ? null
                : attribute.format(DATE_TIME_FORMATTER);
    }
}
