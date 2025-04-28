package com.ziery.ReservasRestaurante.exception;

import lombok.Getter;
import lombok.Setter;

// Representa a estrutura da resposta de erro contendo informações como status, mensagem e timestamp.
@Setter
@Getter
public class ErroResposta {
    private String campo;
    private String mensagem;

    public ErroResposta(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

}
