package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.dtos.MesaDto;
import com.ziery.ReservasRestaurante.dtos.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.ReservaRespostaSucesso;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
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
        ReservaDto resposta = maperarParaMesaDto(reserva);
        return new ReservaRespostaSucesso( "aa", resposta );

        
    }


    //método que mapeia de MesaDto para mesa
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

    //método que mapeia de
    public ReservaDto maperarParaMesaDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus(),
                reserva.getCliente().getId(),
                reserva.getMesa().getId()

        );
    }


}
