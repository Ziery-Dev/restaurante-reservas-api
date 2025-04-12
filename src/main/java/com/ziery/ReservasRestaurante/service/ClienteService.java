package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.ClienteRespostaComMensagem;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.ClienteMapeamento;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    public final ClienteRepository clienteRepository;
    public final ReservaRepository reservaRepository;

    //salvar cliente
    public ClienteRespostaComMensagem salvar(ClienteDto clienteDto) {
        verificaTelefoneOuEmailSalvar(clienteDto.telefone(), clienteDto.email());
        Cliente cliente = ClienteMapeamento.toCliente(clienteDto); // mapeia o clienteDto que chegou como entrada para uma classe do tipo cliente, para que possa ser salvo no repositório
        clienteRepository.save(cliente); //salva o cliente no repositorio
        ClienteDtoReposta clienteReponse = ClienteMapeamento.toClienteDtoResponse(cliente); //mapeia o cliente que foi salvo como Dto novamnete para ser mandado como resposta
        return new ClienteRespostaComMensagem("Cliente salvo com sucesso", clienteReponse); //retorna a mensagem de sucesso junto com o dto para ser retornando no controller como resposta

    }
    //buscar cliente
    public ClienteDtoReposta buscarClientePorId(Long id) {
        var cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(id), id, "Cliente");
        return ClienteMapeamento.toClienteDtoResponse(cliente);

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
        verificaTelefoneOuEmailAtualizar(id, clienteDto.telefone(), clienteDto.email());
        ClienteMapeamento.setarValoresCliente(clienteDto, cliente);
        clienteRepository.save(cliente);
        ClienteDtoReposta clienteReponse = ClienteMapeamento.toClienteDtoResponse(cliente);
        return new ClienteRespostaComMensagem("Cliente atualizado com sucesso", clienteReponse);
    }


    //verificação para dados do cliente repetido ao salvar
    public void verificaTelefoneOuEmailSalvar(String telefone, String email) {
        boolean emailCadastrado = clienteRepository.existsByEmail(email);
        boolean telefoneCadastrado = clienteRepository.existsByTelefone(telefone);
        if (telefoneCadastrado && emailCadastrado ) {
            throw new ViolacaoDeIntegridadeException("O email: " + email +   " e o telefone: " + telefone + "Já foram cadastrados!");
        }
        if (telefoneCadastrado) {
            throw new ViolacaoDeIntegridadeException("O telefone: " + telefone + " Já foi cadastrado!");
        }
        if (emailCadastrado) {
            throw new ViolacaoDeIntegridadeException("O email " + email + " Já foi cadastrado!" );
        }


    }

    //verificação para dados do cliente repetido ao atulizar
    public void verificaTelefoneOuEmailAtualizar(Long id, String telefone,String email) {
        Optional<Cliente> clienteAtualTelefone = clienteRepository.findByTelefone(telefone);
        Optional<Cliente> clienteAtualEmail = clienteRepository.findByEmail(email);
        boolean telefoneEmUso = clienteAtualTelefone.isPresent() && !clienteAtualTelefone.get().getId().equals(id);
        boolean emailEmUso = clienteAtualEmail.isPresent() && !clienteAtualEmail.get().getId().equals(id);

        if (telefoneEmUso && emailEmUso) {
            throw new ViolacaoDeIntegridadeException("O telefone: " + telefone + " E o email" + email + " Já estão em uso!");
        }

        if (telefoneEmUso) {
            throw new ViolacaoDeIntegridadeException("O telefone: " + telefone + " Já  está em uso!");
        }
        if (emailEmUso) {
            throw new ViolacaoDeIntegridadeException("O Email: " + email + " Já está em uso!");
        }

    }








}
