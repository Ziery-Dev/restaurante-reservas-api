package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.ClienteRespostaComMensagem;
import com.ziery.ReservasRestaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController implements GenericController{


    public final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteRespostaComMensagem> cadastrarCliente(@RequestBody  @Valid  ClienteDto clienteDto) {
        ClienteRespostaComMensagem resposta = clienteService.salvar(clienteDto); //manda o cliente para o service e recebe como retorna a resposta de sucesso
        URI location = geraHeaderLocation(resposta.clienteResposta().id());
        return ResponseEntity.created(location).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDtoReposta> buscarClientePorId(@PathVariable Long id) {
         ClienteDtoReposta clienteDtoresposta = clienteService.buscarClientePorId(id);
         return ResponseEntity.status(HttpStatus.OK).body(clienteDtoresposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarClientePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteRespostaComMensagem> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto) {
        ClienteRespostaComMensagem resposta = clienteService.atualizar(clienteDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);

    }


}
