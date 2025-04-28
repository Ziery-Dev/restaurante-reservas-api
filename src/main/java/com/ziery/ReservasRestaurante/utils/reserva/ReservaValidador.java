package com.ziery.ReservasRestaurante.utils.reserva;

import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.entites.Reserva;

import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ReservaValidador {

    private final ReservaRepository reservaRepository;

    public void verificarQuantidade(Long quantidade, int capacidade) {
        if (quantidade > capacidade) {
            throw new ViolacaoDeIntegridadeException(
                    "A quantidade de pessoas na reserva não pode ser maior que a capacidade da mesa. " +
                            "Capacidade da mesa selecionada: " + capacidade
            );
        }
    }

    public void verificarEntidadeJaCadastrada(ReservaDto reservaDto, Long idReserva, String entidade) {
        List<Reserva> reservasRelacionadas = new ArrayList<>();

        if (entidade.equals("Cliente")) {
            reservasRelacionadas = reservaRepository.findByClienteId(reservaDto.idCliente());
        } else if (entidade.equals("Mesa")) {
            reservasRelacionadas = reservaRepository.findByMesaId(reservaDto.idMesa());
        }

        if (!reservasRelacionadas.isEmpty()) {
            for (Reserva reserva : reservasRelacionadas) {
                if (idReserva == null || !reserva.getId().equals(idReserva)) {
                    long diferencaEntreHoras = Math.abs(
                            Duration.between(reserva.getDataHora(), reservaDto.dataHora()).toHours()
                    );
                    if (diferencaEntreHoras < 2) {
                        throw new ViolacaoDeIntegridadeException(
                                "A reserva não pode ser feita porque o(a) " + entidade +
                                        " já está em outra reserva num intervalo de 2 horas"
                        );
                    }
                }
            }
        }
    }
}
