package com.ziery.ReservasRestaurante.mapper;


import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import jakarta.persistence.EntityNotFoundException;
//Essa classe auxilia no mapeamento Dto para Entidade e Entidade para Dto
public class ReservaMapeamento {

    public static Reserva toReserva(ReservaDto reservaDto, Mesa mesa, Cliente cliente) {
        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setCliente(cliente);
        reserva.setDataHora(reservaDto.dataHora());
        reserva.setStatus(reservaDto.status());
        reserva.setQuantidadePessoas(reservaDto.quantidadePessoas());
        return reserva;
    }

    public static ReservaDto toReservaDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus(),
                reserva.getCliente().getId(),
                reserva.getMesa().getId()

        );
    }

    public static Reserva atualizarReserva (ReservaDto reservaDto, Reserva reserva, Cliente cliente, Mesa mesa) {
        reserva.setMesa(mesa);
        reserva.setCliente(cliente);
        reserva.setDataHora(reservaDto.dataHora());
        reserva.setStatus(reservaDto.status());
        return reserva;
    }


}
