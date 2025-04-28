package com.ziery.ReservasRestaurante.dtos.response;


import com.ziery.ReservasRestaurante.entites.Status;

import java.time.LocalDateTime;
// Classe responsável por representar os dados da reserva retornados como resposta.
public record ReservaDtoResposta(Long id, LocalDateTime dataHora, Long quantidadePessoas, Status status, Long idCliente, Long idMesa ) {
}
