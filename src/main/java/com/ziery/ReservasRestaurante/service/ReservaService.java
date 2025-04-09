package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRespostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.mapper.ReservaMapeamento;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService {

    private ReservaRepository reservaRepository;
    private MesaRepository mesaRepository;
    private ClienteRepository clienteRepository;

    //método salvar
    public ReservaRespostaSucesso salvar(ReservaDto reservaDto){
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        Reserva reserva = ReservaMapeamento.toReserva(reservaDto, mesa, cliente); //mapeia de os dados do Dto para entidade
        reservaRepository.save(reserva);
        ReservaDto resposta = ReservaMapeamento.toReservaDto(reserva); //mapeia de entidade para dto
        return new ReservaRespostaSucesso( "Reserva salva com sucesso! ", resposta );
    }

    //Exibir reserva por id
    public ReservaDto buscarReservaId(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        return ReservaMapeamento.toReservaDto(reserva);
    }

    //excluir reserva por id
    public void deletarReserva(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        reservaRepository.delete(reserva);
    }

    //atualizar reserva
    public ReservaRespostaSucesso atualizarReserva(Long id, ReservaDto reservaDto){
        Reserva reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        Reserva reservaMapeada = ReservaMapeamento.setarValoresReserva(reservaDto, reserva, cliente, mesa);
        ReservaDto resposta = ReservaMapeamento.toReservaDto(reservaMapeada);
        return new ReservaRespostaSucesso("Reserva atualizada com sucesso", resposta );
    }








}
