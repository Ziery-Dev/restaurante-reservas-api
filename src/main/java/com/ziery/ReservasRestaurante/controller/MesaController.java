package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.MesaDto;
import com.ziery.ReservasRestaurante.dtos.MesaDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.service.MesaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/mesa")
public class MesaController {

    //Injeção via lombok
    private final MesaService mesaService;


    @PostMapping
    public ResponseEntity<MesaDtoRepostaSucesso> salvar(@RequestBody @Valid MesaDto mesaDto) {
        MesaDtoRepostaSucesso response = mesaService.salvar(mesaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
