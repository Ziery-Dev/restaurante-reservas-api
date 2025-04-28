package com.ziery.ReservasRestaurante.exception;

// Exceção personalizada para recursos não encontrados no banco de dados.
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
