package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.ClienteDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ClienteService {

    public final ClienteRepository clienteRepository;

    //salvar cliente
    public ClienteDtoRepostaSucesso salvar(ClienteDto clienteDto) {
        Cliente cliente = mapearParaCliente(clienteDto); // mapeia o clienteDto que chegou como entrada para uma classe do tipo cliente, para que possa ser salvo no repositório
        clienteRepository.save(cliente); //salva o cliente no repositorio
        ClienteDto clienteReponse = mapearParaClienteDto(cliente); //mapeia o cliente que foi salvo como Dto novamnete para ser mandado como resposta
        return new ClienteDtoRepostaSucesso("Cliente salvo com sucesso", clienteReponse); //retorna a mensagem de sucesso junto com o dto para ser retornando no controller como resposta

    }


    //método que mapeia de ClienteDto para Cliente
    public Cliente mapearParaCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.nome());
        cliente.setTelefone(clienteDto.telefone());
        cliente.setEmail(clienteDto.email());
        return cliente;
    }

    //método que mapeia de  Cliente para ClienteDto
    public static ClienteDto mapearParaClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataCadastro()

        );
    }

}
