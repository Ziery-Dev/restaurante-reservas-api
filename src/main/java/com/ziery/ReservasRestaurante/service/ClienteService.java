package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.ClienteMapeamento;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    public final ClienteRepository clienteRepository;
    public final ReservaRepository reservaRepository;

    //salvar cliente
    public ClienteDtoRepostaSucesso salvar(ClienteDto clienteDto) {
        Cliente cliente = ClienteMapeamento.toCliente(clienteDto); // mapeia o clienteDto que chegou como entrada para uma classe do tipo cliente, para que possa ser salvo no repositório
        clienteRepository.save(cliente); //salva o cliente no repositorio
        ClienteDto clienteReponse = ClienteMapeamento.toClienteDto(cliente); //mapeia o cliente que foi salvo como Dto novamnete para ser mandado como resposta
        return new ClienteDtoRepostaSucesso("Cliente salvo com sucesso", clienteReponse); //retorna a mensagem de sucesso junto com o dto para ser retornando no controller como resposta

    }
    //buscar cliente
    public ClienteDto buscarClientePorId(Long id) {
        var cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        return ClienteMapeamento.toClienteDto(cliente);

    }

    //deletar cliente
    public void deletarClientePorId(Long id) {
       var cliente =  VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        if (reservaRepository.existsByClienteId(cliente.getId())) {
            throw new ViolacaoDeIntegridadeException("Cliente com Id " + id + " não pode ser deletado pois está vinculada a uma ou mais reservas");
        }
        clienteRepository.deleteById(cliente.getId());

    }

    //Atualizar cliente
    public ClienteDtoRepostaSucesso atualizar(ClienteDto clienteDto, Long id) {
        var cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        ClienteMapeamento.atualizarCliente(clienteDto, cliente);
        clienteRepository.save(cliente);
        ClienteDto clienteReponse = ClienteMapeamento.toClienteDto(cliente);
        return new ClienteDtoRepostaSucesso("Cliente atualizado com sucesso", clienteReponse);
    }







}
