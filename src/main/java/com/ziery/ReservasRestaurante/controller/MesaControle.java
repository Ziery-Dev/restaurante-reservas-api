package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.MesaRespostaComMensagem;
import com.ziery.ReservasRestaurante.service.MesaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/mesas")
public class MesaControle implements ControleGenerico {

    //Injeção de dependência via lombok
    private final MesaService mesaService;


    @PostMapping
    public ResponseEntity<MesaRespostaComMensagem> salvarMesa(@RequestBody @Valid MesaDto mesaDto) {
        MesaRespostaComMensagem response = mesaService.salvarMesa(mesaDto);
        URI uri = geraHeaderLocation(response.mesa().id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDtoReposta> buscarMesaPorId(@PathVariable Long id) {
        MesaDtoReposta mesaDtoReposta = mesaService.buscarMesaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mesaDtoReposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerMesaPorId(@PathVariable Long id) {
        mesaService.removerMesaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaRespostaComMensagem> atualizarMesa(@PathVariable Long id, @RequestBody @Valid MesaDto mesaDto) {
       MesaRespostaComMensagem resposta =  mesaService.atualizarMesa(id, mesaDto);
       return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

}
