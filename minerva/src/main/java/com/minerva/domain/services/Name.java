package com.minerva.domain.services;

public class Name {
    public static Result<Void> validateFormat(String value, int minLength, int maxLength) {
        if (value == null || value.isBlank()) return Result.fail("El NOMBRE no puede estar vacío.");
        if (value.length() < minLength) return Result.fail("El NOMBRE debe tener al menos " + minLength + " caracteres.");
        if (value.length() > maxLength) return Result.fail("El NOMBRE no puede exceder los " + maxLength + " caracteres.");
        if (!value.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) return Result.fail("El NOMBRE solo debe contener letras.");

        return Result.success(null);
    }
}