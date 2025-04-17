package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.ClienteRespostaComMensagem;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.ClienteMapper;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.cliente.ClienteValidador;
import com.ziery.ReservasRestaurante.utils.global.VerificadorEntidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteValidador clienteValidador;

    //salvar cliente
    public ClienteRespostaComMensagem salvar(ClienteDto clienteDto) {
        clienteValidador.verificarEmailETelefone(null, clienteDto.telefone(), clienteDto.email() );
        Cliente cliente = clienteMapper.toCliente(clienteDto);// mapeia o clienteDto que chegou como entrada para uma classe do tipo cliente, para que possa ser salvo no repositório
        clienteRepository.save(cliente); //salva o cliente no repositorio
        ClienteDtoReposta clienteReponse = clienteMapper.toClienteDtoResposta(cliente) ;//mapeia o cliente que foi salvo como Dto novamnete para ser mandado como resposta
        return new ClienteRespostaComMensagem("Cliente salvo com sucesso", clienteReponse); //retorna a mensagem de sucesso junto com o dto para ser retornando no controller como resposta

    }
    //buscar cliente
    public ClienteDtoReposta buscarClientePorId(Long id) {
        var cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        return clienteMapper.toClienteDtoResposta(cliente);

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
    public ClienteRespostaComMensagem atualizar(ClienteDto clienteDto, Long id) {
        var cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        clienteValidador.verificarEmailETelefone(id, clienteDto.telefone(), clienteDto.email() );
        clienteMapper.ClientSetValores(clienteDto, cliente);
        clienteRepository.save(cliente);
        ClienteDtoReposta clienteReponse = clienteMapper.toClienteDtoResposta(cliente);
        return new ClienteRespostaComMensagem("Cliente atualizado com sucesso", clienteReponse);
    }







}
