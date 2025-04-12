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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/mesas")
public class MesaController implements GenericController {

    //Injeção via lombok
    private final MesaService mesaService;


    @PostMapping
    public ResponseEntity<MesaRespostaComMensagem> salvar(@RequestBody @Valid MesaDto mesaDto) {
        MesaRespostaComMensagem response = mesaService.salvar(mesaDto);
        URI uri = geraHeaderLocation(response.mesa().id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDtoReposta> buscarPorId(@PathVariable Long id) {
        MesaDtoReposta mesaDtoReposta = mesaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mesaDtoReposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMesa(@PathVariable Long id) {
        mesaService.excluirMesa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaRespostaComMensagem> atualizarMesa(@PathVariable Long id, @RequestBody @Valid MesaDto mesaDto) {
       MesaRespostaComMensagem resposta =  mesaService.atualizarMesa(id, mesaDto);
       return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

}
