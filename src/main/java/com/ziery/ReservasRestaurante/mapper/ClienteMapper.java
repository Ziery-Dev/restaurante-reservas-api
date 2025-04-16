package com.ziery.ReservasRestaurante.mapper;

import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.entites.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // dto para entidade
    Cliente toCliente(ClienteDto clienteDto);

    //entidade para dtoResposta
    ClienteDtoReposta toClienteDtoResposta(Cliente cliente);

    //lista entidade para lista dtoResposta
    List<ClienteDtoReposta> toClienteDtoRespostaList(List<Cliente> clientes);


    // cliente dto para entidade, sem perder a referencia da entidade (para atualização Put)
    void ClientSetValores(ClienteDto clienteDto, @MappingTarget Cliente cliente);



}
