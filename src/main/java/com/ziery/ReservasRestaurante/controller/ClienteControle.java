package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.ClienteRespostaComMensagem;
import com.ziery.ReservasRestaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteControle implements ControleGenerico {


    public final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteRespostaComMensagem> salvarCliente(@RequestBody  @Valid  ClienteDto clienteDto) {
        ClienteRespostaComMensagem resposta = clienteService.salvarCliente(clienteDto);
        URI location = geraHeaderLocation(resposta.clienteResposta().id());
        return ResponseEntity.created(location).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDtoReposta> buscarClientePorId(@PathVariable Long id) {
         ClienteDtoReposta clienteDtoresposta = clienteService.buscarClientePorId(id);
         return ResponseEntity.status(HttpStatus.OK).body(clienteDtoresposta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerClientePorId(@PathVariable Long id) {
        clienteService.removerClientePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteRespostaComMensagem> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto) {
        ClienteRespostaComMensagem resposta = clienteService.atualizarCliente(clienteDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);

    }

    @GetMapping
    public ResponseEntity<Page<ClienteDtoReposta>> listarClientes(
            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer tamanho,
             @RequestParam(value = "pagina", defaultValue = "0")
             Integer pagina) {

        var result =  clienteService.listarTodos(pagina, tamanho);
        return ResponseEntity.status(HttpStatus.OK).body(result);



    }


}
