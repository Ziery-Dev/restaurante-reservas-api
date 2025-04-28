package com.ziery.ReservasRestaurante.exception;

// Exceção personalizada para violações de integridade, como dados duplicados ou inválidos.
public class ViolacaoDeIntegridadeException extends RuntimeException {
    public ViolacaoDeIntegridadeException(String mensagem) {
        super(mensagem);
    }
}
