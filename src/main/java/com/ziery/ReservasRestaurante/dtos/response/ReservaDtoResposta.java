package com.ziery.ReservasRestaurante.dtos.response;


import com.ziery.ReservasRestaurante.dtos.request.MesaDto;
import com.ziery.ReservasRestaurante.entites.Status;

import java.time.LocalDateTime;

public record ReservaDtoResposta(Long id, LocalDateTime dataHora, Long quantidadePessoas, Status status, Long idCliente, Long idMesa ) {
}
