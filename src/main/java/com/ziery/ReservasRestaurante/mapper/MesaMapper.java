package com.ziery.ReservasRestaurante.mapper;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoReposta;
import com.ziery.ReservasRestaurante.entites.Mesa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    // dto para entidade
    Mesa toMesa(MesaDto mesaDto);

    //entidade para dtoResposta
    MesaDtoReposta toMesaDtoResposta(Mesa mesa);

    //lista entidade para lista dtoResposta
    List<MesaDtoReposta> toMesaDtoRespostaList(List<Mesa> mesas);

    // mesa dto para entidade, sem perder a referencia da entidade (para atualização Put)
    void MesaSetValores(MesaDto mesaDto, @MappingTarget Mesa mesa);



}
