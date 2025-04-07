package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {


    public final ClienteRepository clienteRepository;
    public final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDtoRepostaSucesso> cadastrarCliente(@RequestBody  @Valid  ClienteDto clienteDto) {
        ClienteDtoRepostaSucesso resposta = clienteService.salvar(clienteDto); //manda o cliente para o service e recebe como retorna a resposta de sucesso
       return ResponseEntity.status(HttpStatus.CREATED).body(resposta); //retorna a resposta de sucesso se os dados estiverem corretos
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
         ClienteDto clienteDto = clienteService.buscarClientePorId(id);
         return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarClientePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDtoRepostaSucesso> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto) {
        ClienteDtoRepostaSucesso resposta = clienteService.atualizar(clienteDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);

    }


}
