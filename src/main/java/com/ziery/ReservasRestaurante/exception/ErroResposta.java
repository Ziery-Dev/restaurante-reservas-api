package com.ziery.ReservasRestaurante.exception;

import lombok.Getter;
import lombok.Setter;

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
