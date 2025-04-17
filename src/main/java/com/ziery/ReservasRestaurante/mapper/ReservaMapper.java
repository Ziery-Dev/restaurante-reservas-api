package com.ziery.ReservasRestaurante.mapper;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaDtoResposta;
import com.ziery.ReservasRestaurante.entites.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    // DTO para entidade
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    Reserva toReserva(ReservaDto reservaDto);

    // Entidade para DTO de Resposta
    @Mapping(source = "cliente.id", target = "idCliente")
    @Mapping(source = "mesa.id", target = "idMesa")
    ReservaDtoResposta toReservaDtoResposta(Reserva reserva);

    // Lista de entidades para lista de DTOs de Resposta
    List<ReservaDtoResposta> toReservaDtoRespostaList(List<Reserva> reservas);

    // Atualiza uma entidade Reserva com os valores do DTO (sem perder referência)
        @Mapping(target = "cliente", ignore = true)
        @Mapping(target = "mesa", ignore = true)
    void updateFromDto(ReservaDto reservaDto, @MappingTarget Reserva reserva);

}

q