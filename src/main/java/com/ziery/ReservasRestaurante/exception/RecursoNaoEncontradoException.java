package com.ziery.ReservasRestaurante.exception;

//Exceção para quando um recurso não é econtrado no banco (findById(id))
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
