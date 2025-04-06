package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.ClienteDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.exception.RecursoNaoEncontradoException;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    //buscar cliente
    public ClienteDto buscarClientePorId(Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException( "Cliente com Id: "+ id + " não encontrado"));
        return mapearParaClienteDto(cliente);

    }

    //deletar cliente
    public void deletarClientePorId(Long id) {
        var cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()){
            throw new RecursoNaoEncontradoException("Cliente com Id: "+ id + " não encontrado para ser deletado");
        }
        clienteRepository.deleteById(id);

    }

    //Atualizar cliente
    public ClienteDtoRepostaSucesso atualizar(ClienteDto clienteDto, Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com Id "+ id + " não encontrado"));
        cliente.setNome(clienteDto.nome());
        cliente.setEmail(clienteDto.email());
        cliente.setTelefone(clienteDto.telefone());
        clienteRepository.save(cliente);
        ClienteDto clienteReponse = mapearParaClienteDto(cliente);
        return new ClienteDtoRepostaSucesso("Cliente atualizado com sucesso", clienteReponse);
    }



    //método que mapeia de ClienteDto para Cliente
    public  Cliente mapearParaCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.nome());
        cliente.setTelefone(clienteDto.telefone());
        cliente.setEmail(clienteDto.email());
        return cliente;
    }

    //método que mapeia de  Cliente para ClienteDto
    public  ClienteDto mapearParaClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()

        );
    }



}
