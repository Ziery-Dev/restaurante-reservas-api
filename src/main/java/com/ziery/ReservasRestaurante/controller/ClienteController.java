package com.ziery.ReservasRestaurante.controller;

import com.ziery.ReservasRestaurante.dtos.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.ClienteDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
