package com.ziery.ReservasRestaurante.utils;

import com.ziery.ReservasRestaurante.exception.ErroResposta;
import com.ziery.ReservasRestaurante.exception.RecursoNaoEncontradoException;

import java.util.Optional;

//Classe auxiliar que faz a busca do objeto no repositorio ou lança exceção
public class VerificadorEntidade {
    public static <T> T verificarOuLancarException(Optional<T> entidade, Long id ) {
        return entidade.orElseThrow(() -> new RecursoNaoEncontradoException("A entidade com id  " + id + " não foi encontrado" ));
    }
}
