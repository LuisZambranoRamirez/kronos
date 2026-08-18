package com.minerva.domain.exceptions;

/**
 * Excepción de dominio lanzada cuando un valor numérico o monetario viola el límite
 * mínimo establecido por las reglas de negocio del dominio.
 */
public class MinimumAmountException extends InvalidDomainArgumentException {

    public MinimumAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumAmountException(String message) {
        super(message);
    }
}
