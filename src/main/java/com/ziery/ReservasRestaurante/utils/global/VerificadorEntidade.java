package com.ziery.ReservasRestaurante.utils.global;

import com.ziery.ReservasRestaurante.exception.RecursoNaoEncontradoException;

import java.util.Optional;

//Classe auxiliar que faz a busca do objeto no repositorio ou lança exceção
public class VerificadorEntidade {
    public static <T> T verificarOuLancarException(Optional<T> entidade, Long id, String nomeEntidade ) {
        return entidade.orElseThrow(() -> new RecursoNaoEncontradoException(nomeEntidade + " com id  " + id + " não foi encontrado" ));
    }
}
