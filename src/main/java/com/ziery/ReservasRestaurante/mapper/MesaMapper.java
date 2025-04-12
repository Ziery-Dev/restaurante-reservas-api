package com.ziery.ReservasRestaurante.mapper;


import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoReposta;
import com.ziery.ReservasRestaurante.entites.Mesa;

//Essa classe auxilia no mapeamento Dto para Entidade e Entidade para Dto
public class MesaMapper {

    public static Mesa toMesa(MesaDto mesaDto) {
       Mesa mesa = new Mesa();
        return setarValoresMesa(mesaDto, mesa); //chama o metodo de atualizar somente para setar os valores
    }

    public static MesaDtoReposta toMesaDto(Mesa mesa) {
        return new MesaDtoReposta(
                mesa.getId(),
                mesa.getNumero(),
                mesa.getCapacidade()
        );
    }

    public static Mesa setarValoresMesa (MesaDto mesaDto, Mesa mesa) {
        mesa.setCapacidade(mesaDto.capacidade());
        mesa.setNumero(mesaDto.numero());
        return mesa;
    }


}
