package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaDtoResposta;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRepostaComMensagem;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.mapper.ReservaMapper;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.global.VerificadorEntidade;
import com.ziery.ReservasRestaurante.utils.reserva.ReservaValidador;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService  {

    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;
    private final ReservaMapper reservaMapper;
    private final ReservaValidador reservaValidacoes;

    //Salva nova mesa
    public ReservaRepostaComMensagem salvarReserva(ReservaDto reservaDto){
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        reservaValidacoes.verificarEntidadeJaCadastrada(reservaDto, null, "Mesa");
        reservaValidacoes.verificarEntidadeJaCadastrada(reservaDto, null, "Cliente");
        reservaValidacoes.verificarQuantidade(reservaDto.quantidadePessoas(), mesa.getCapacidade());
        Reserva reserva = reservaMapper.toReserva(reservaDto);  //mapeia de os dados do Dto para entidade
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reservaRepository.save(reserva);
        ReservaDtoResposta resposta = reservaMapper.toReservaDtoResposta(reserva); //mapeia de entidade para dto
        return new ReservaRepostaComMensagem( "Reserva salva com sucesso! ", resposta );
    }

    //Exibir reserva por id
    public ReservaDtoResposta buscarReservaPorId(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        return reservaMapper.toReservaDtoResposta(reserva);
    }

    //excluir reserva por id
    public void removerReservaPorId(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        reservaRepository.delete(reserva);
    }

    //atualizar reserva
    public ReservaRepostaComMensagem atualizarReserva(Long id, ReservaDto reservaDto){
        Reserva reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        reservaValidacoes.verificarEntidadeJaCadastrada(reservaDto, id, "Cliente");
        reservaValidacoes.verificarEntidadeJaCadastrada(reservaDto, id, "Mesa");
        reservaValidacoes.verificarQuantidade(reservaDto.quantidadePessoas(), mesa.getCapacidade());
        reservaMapper.updateFromDto(reservaDto, reserva);
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reservaRepository.save(reserva);
        ReservaDtoResposta resposta = reservaMapper.toReservaDtoResposta(reserva);
        return new ReservaRepostaComMensagem("Reserva atualizada com sucesso!", resposta );
    }

    //lista todas as reservas com paginação
    public Page<ReservaDtoResposta> listarTodos(Integer quantidade, Integer numero) {

        Pageable pageRequest = PageRequest.of(numero, quantidade );
        var resultado = reservaRepository.findAll(pageRequest);
        return resultado.map(reservaMapper::toReservaDtoResposta);
    }











}
