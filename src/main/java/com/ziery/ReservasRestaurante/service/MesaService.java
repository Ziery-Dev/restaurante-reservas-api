package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.MesaDto;
import com.ziery.ReservasRestaurante.dtos.MesaDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@AllArgsConstructor

public class MesaService {

    private final MesaRepository mesaRepository;


    //salvar mesa
    public MesaDtoRepostaSucesso salvar(@RequestBody MesaDto mesaDto) {
        Mesa mesa = mapearParaMesa(mesaDto);
        mesaRepository.save(mesa);
        MesaDto resposta = mapearParaMesaDto(mesa);
        return new MesaDtoRepostaSucesso("Mesa salva com sucesso", resposta);

    }


    //método que mapeia de ClienteDto para Cliente
    public Mesa mapearParaMesa(MesaDto mesaDto) {
        Mesa mesa = new Mesa();
        mesa.setNumero(mesaDto.numero());
        mesa.setCapacidade(mesaDto.capacidade());
        return mesa;
    }

    //método que mapeia de  Mesa para MesaDto
    public MesaDto mapearParaMesaDto(Mesa mesa) {
        return new MesaDto(
                mesa.getNumero(),
                mesa.getCapacidade()
        );
    }
}
