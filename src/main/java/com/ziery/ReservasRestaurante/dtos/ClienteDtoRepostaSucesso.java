package com.ziery.ReservasRestaurante.dtos;

import com.ziery.ReservasRestaurante.entites.Cliente;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ClienteDtoRepostaSucesso {
    private String resposta;
    private ClienteDto cliente;


    public  ClienteDtoRepostaSucesso( String reposta, ClienteDto cliente) {
        this.resposta = reposta;
        this.cliente = cliente;

    }

}
