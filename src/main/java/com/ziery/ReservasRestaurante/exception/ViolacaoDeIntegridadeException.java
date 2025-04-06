package com.ziery.ReservasRestaurante.exception;

public class ViolacaoDeIntegridadeException extends RuntimeException {
    public ViolacaoDeIntegridadeException(String mensagem) {
        super(mensagem);
    }
}
