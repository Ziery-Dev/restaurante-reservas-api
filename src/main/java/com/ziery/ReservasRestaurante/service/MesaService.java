package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.response.MesaDtoReposta;
import com.ziery.ReservasRestaurante.dtos.response.MesaRespostaComMensagem;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.MesaMapper;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.global.VerificadorEntidade;
import com.ziery.ReservasRestaurante.utils.mesa.MesaValidador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor

public class MesaService {

    private final MesaRepository mesaRepository;
    private final ReservaRepository reservaRepository;
    private final MesaMapper mesaMapper;
    private final MesaValidador mesaValidador;




    //salvar mesa
    public MesaRespostaComMensagem salvarMesa(@RequestBody MesaDto mesaDto) {
        mesaValidador.validarNumeroMesa(mesaDto.numero(), null);
        Mesa mesa = mesaMapper.toMesa(mesaDto);
        mesaRepository.save(mesa);
        MesaDtoReposta resposta = mesaMapper.toMesaDtoResposta(mesa);
        return new MesaRespostaComMensagem("Mesa salva com sucesso", resposta);

    }
    //Exibir mesa por Id
    public MesaDtoReposta buscarMesaPorId(Long id) {
        var mesa =  VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa");
        return mesaMapper.toMesaDtoResposta(mesa);

    }

    //deletar mesa
    public void removerMesaPorId(Long id) {
        var mesa =  VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa");
        if (reservaRepository.existsByMesaId(mesa.getId())) {
            throw new ViolacaoDeIntegridadeException("Mesa com Id " + id + " não pode ser deletada pois está vinculada a uma ou mais reservas");
        }
        mesaRepository.deleteById(mesa.getId());
    }

    //Atualizar mesa
    public MesaRespostaComMensagem atualizarMesa(Long id, MesaDto mesaDto) {
        var mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(id), id, "Mesa" );
        mesaValidador.validarNumeroMesa(mesaDto.numero(), id);
        mesaMapper.MesaSetValores(mesaDto, mesa);
        mesaRepository.save(mesa);
        MesaDtoReposta mesaReposta = mesaMapper.toMesaDtoResposta(mesa);
        return new MesaRespostaComMensagem("Mesa atualizada com sucesso ", mesaReposta);

    }




}
