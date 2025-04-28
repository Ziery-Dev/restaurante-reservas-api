package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaDtoResposta;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRepostaComMensagem;
import com.ziery.ReservasRestaurante.service.ReservaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
public class ReservaControle implements ControleGenerico {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaRepostaComMensagem> salvarReserva(@RequestBody @Valid ReservaDto reservaDto) {
        var resposta = reservaService.salvarReserva(reservaDto);
        URI location = geraHeaderLocation(resposta.reserva().id());
        return ResponseEntity.created(location).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDtoResposta> buscarReservaPorId(@PathVariable Long id) {
        ReservaDtoResposta resposta = reservaService.buscarReservaPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReservaPorId(@PathVariable Long id) {
        reservaService.removerReservaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaRepostaComMensagem> atualizarReserva(@PathVariable Long id, @RequestBody @Valid ReservaDto reservaDto) {
        ReservaRepostaComMensagem response = reservaService.atualizarReserva(id, reservaDto);
        return ResponseEntity.ok(response);
    }


}
