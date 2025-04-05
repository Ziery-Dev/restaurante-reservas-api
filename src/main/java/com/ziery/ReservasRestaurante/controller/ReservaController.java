package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.ReservaRespostaSucesso;
import com.ziery.ReservasRestaurante.service.ReservaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaRespostaSucesso> salvar(@RequestBody @Valid ReservaDto reservaDto) {
        var resposta = reservaService.salvar(reservaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }


}
