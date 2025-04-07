package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRespostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
import jakarta.persistence.EntityNotFoundException;
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
        Reserva reserva = mapearParaReserva(reservaDto);
        reservaRepository.save(reserva);
        ReservaDto resposta = maperaReservaDto(reserva);
        return new ReservaRespostaSucesso( "Reserva salva com sucesso! ", resposta );
    }

    //Exibir reserva por id
    public ReservaDto buscarReservaId(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id);
        return maperaReservaDto(reserva);
    }

    //excluir reserva por id
    public void deletarReserva(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id);
        reservaRepository.delete(reserva);
    }

    //atualizar reserva
    public ReservaRespostaSucesso atualizarReserva(Long id, ReservaDto reservaDto){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id);
        reserva.setQuantidadePessoas(reservaDto.quantidadePessoas());
        reserva.setStatus(reservaDto.status());
        reserva.setDataHora(reservaDto.dataHora());
        reserva.setCliente(clienteRepository.findById(reservaDto.idCliente()).orElseThrow( () -> new EntityNotFoundException("Cliente ID " + reservaDto.idCliente() +" não econtrado")));
        reserva.setMesa( mesaRepository.findById(reservaDto.idMesa()).orElseThrow(() -> new EntityNotFoundException("Mesa ID " + reservaDto.idMesa() + " não econtrado")));
        reservaRepository.save(reserva);
        ReservaDto resposta = maperaReservaDto(reserva);
        return new ReservaRespostaSucesso("Reserva atualizado com sucesso", resposta );
    }


    //método que mapeia de reversaDto para mesa
    public Reserva mapearParaReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();

        Mesa idMesa = mesaRepository.findById(reservaDto.idMesa()).orElseThrow(() -> new EntityNotFoundException("Mesa ID " + reservaDto.idMesa() + " não econtrado"));
        reserva.setMesa(idMesa);

        Cliente idCliente = clienteRepository.findById(reservaDto.idCliente()).orElseThrow(() -> new EntityNotFoundException("Cliente ID " + reservaDto.idCliente() +" não econtrado"));
        reserva.setCliente(idCliente);


       reserva.setDataHora(reservaDto.dataHora());


        reserva.setStatus(reservaDto.status());
        reserva.setQuantidadePessoas(reservaDto.quantidadePessoas());
        return reserva;
    }

    //método que mapeia de reservaDto Para Reserva
    public ReservaDto maperaReservaDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus(),
                reserva.getCliente().getId(),
                reserva.getMesa().getId()

        );
    }


}
