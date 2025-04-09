package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoRepostaSucesso;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.MesaMapper;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
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
        Mesa mesa = MesaMapper.toMesa(mesaDto);
        mesaRepository.save(mesa);
        MesaDto resposta = MesaMapper.toMesaDto(mesa);
        return new MesaDtoRepostaSucesso("Mesa salva com sucesso", resposta);

    }
    //Exibir mesa por Id
    public MesaDto buscarPorId(Long id) {
        var mesa =  VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa");
        return MesaMapper.toMesaDto(mesa);

    }

    //deletar mesa
    public void excluirMesa(Long id) {
        var mesa =  VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa");
        if (reservaRepository.existsByMesaId(mesa.getId())) {
            throw new ViolacaoDeIntegridadeException("Mesa com Id " + id + " não pode ser deletada pois está vinculada a uma ou mais reservas");
        }
        mesaRepository.deleteById(mesa.getId());
    }

    //Atualizar mesa
    public MesaDtoRepostaSucesso atualizarMesa(Long id, MesaDto mesaDto) {
        var mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa" );
        MesaMapper.setarValoresMesa(mesaDto, mesa); //seta os valores de mesaDto em mesa
        mesaRepository.save(mesa);
        MesaDto mesaReposta = MesaMapper.toMesaDto(mesa);
        return new MesaDtoRepostaSucesso("Mesa atualizada com sucesso ", mesaReposta);

    }



}
