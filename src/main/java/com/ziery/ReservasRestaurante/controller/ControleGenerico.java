package com.ziery.ReservasRestaurante.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
//classe responsável por gerar URI location. podendo ser usada ao implementar (implements) na classe controle desejada
public interface ControleGenerico {
    default URI geraHeaderLocation(Long id){
            return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();
    }
}
