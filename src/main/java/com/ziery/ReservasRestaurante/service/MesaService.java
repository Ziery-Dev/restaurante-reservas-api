package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.MesaDto;
import com.ziery.ReservasRestaurante.dtos.MesaDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.exception.RecursoNaoEncontradoException;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor

public class MesaService {

    private final MesaRepository mesaRepository;
    private final ReservaRepository reservaRepository;



    //salvar mesa
    public MesaDtoRepostaSucesso salvar(@RequestBody MesaDto mesaDto) {
        Mesa mesa = mapearParaMesa(mesaDto);
        mesaRepository.save(mesa);
        MesaDto resposta = mapearParaMesaDto(mesa);
        return new MesaDtoRepostaSucesso("Mesa salva com sucesso", resposta);

    }
    //Exibir mesa por Id
    public MesaDto buscarPorId(Long id) {
        var mesa = mesaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Mesa com id " + id + " não encontrado"));
        return mapearParaMesaDto(mesa);

    }

    //deletar mesa
    public void excluirMesa(Long id) {
        var mesa = mesaRepository.findById(id);
        if(mesa.isEmpty()){
            throw new RecursoNaoEncontradoException("Mesa com id " + id + " não encontrado");
        }
        if (reservaRepository.existsByMesaId(id)) {
            throw new ViolacaoDeIntegridadeException("Mesa com Id " + id + " não pode ser deletada pois está vinculada a uma ou mais reservas");
        }
        mesaRepository.deleteById(id);

    }

    //Atualizar mesa
    public MesaDtoRepostaSucesso atualizarMesa(Long id, @RequestBody MesaDto mesaDto) {
        var mesa = mesaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Mesa com id " + id + " não encontrado"));
        mesa.setNumero(mesaDto.numero());
        mesa.setCapacidade(mesaDto.capacidade());
        mesaRepository.save(mesa);
        MesaDto mesaReposta = mapearParaMesaDto(mesa);
        return new MesaDtoRepostaSucesso("Mesa atualizada com sucesso ", mesaReposta);


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
