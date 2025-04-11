package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoRepostaSucesso;
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
public class MesaController {

    //Injeção via lombok
    private final MesaService mesaService;


    @PostMapping
    public ResponseEntity<MesaDtoRepostaSucesso> salvar(@RequestBody @Valid MesaDto mesaDto) {
        MesaDtoRepostaSucesso response = mesaService.salvar(mesaDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/id")
                .buildAndExpand(response.resposta())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDto> buscarPorId(@PathVariable Long id) {
        MesaDto mesaDto = mesaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMesa(@PathVariable Long id) {
        mesaService.excluirMesa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDtoRepostaSucesso> atualizarMesa(@PathVariable Long id, @RequestBody @Valid MesaDto mesaDto) {
       MesaDtoRepostaSucesso resposta =  mesaService.atualizarMesa(id, mesaDto);
       return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

}
