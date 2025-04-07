package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRespostaSucesso;
import com.ziery.ReservasRestaurante.service.ReservaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> buscarReserva(@PathVariable Long id) {
        ReservaDto resposta = reservaService.buscarReservaId(id);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaRespostaSucesso> atualizarReserva(@PathVariable Long id, @RequestBody @Valid ReservaDto reservaDto) {
        ReservaRespostaSucesso response = reservaService.atualizarReserva(id, reservaDto);
        return ResponseEntity.ok(response);
    }


}
