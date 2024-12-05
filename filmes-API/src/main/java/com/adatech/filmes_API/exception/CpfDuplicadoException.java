package com.adatech.filmes_API.exception;

public class CpfDuplicadoException extends RuntimeException {
    public CpfDuplicadoException(String message) {
        super(message);
    }
}
