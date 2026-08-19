package com.minerva.domain.services;

// Nota: chat gpt recomienda usar la palbra fuilure en vez de fail, porque dice que failure es sutatntivo
public class Result<D> {
    private final boolean success;
    private final ErrorType errorType;
    private final String message;
    private final D data;

    private Result(boolean success, ErrorType errorType, String message, D data) {
        this.success = success;
        this.errorType = errorType;
        this.message = message;
        this.data = data;
    }

    public static <D> Result<D> success(D data) {
        return new Result<>(true, ErrorType.NONE, "", data);
    }

    public static <D> Result<D> fail(String message) {
        return new Result<>(false, ErrorType.GENERIC, message, null);
    }

    public static <D> Result<D> fail(ErrorType errorType, String message) {
        return new Result<>(false, errorType, message, null);
    }

    public boolean isSuccess() { return success; }
    public ErrorType getErrorType() {
        return errorType;
    }
    public boolean isFail() {return !success;}
    public String getMessage() { return message; }
    public D getData() { return data; }

    public enum ErrorType {
        NONE,
        GENERIC,
        UNAUTHORIZED_ACTION
    }
}
