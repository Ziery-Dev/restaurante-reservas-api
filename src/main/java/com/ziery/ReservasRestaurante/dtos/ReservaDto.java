package com.ziery.ReservasRestaurante.dtos;

import com.ziery.ReservasRestaurante.entites.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaDto (@NotNull(message = "A data e hora da reserva não pode ficar vazia") @Future(message = "A data deve ser futura") LocalDateTime dataHora,
                          @NotNull(message = "A quantidade de pessoas não pode ficar vazia") Long quantidadePessoas,
                          @NotNull(message = "O status não deve ser nulo") Status status,
                          @NotNull (message = "O Id do cliente não pode ser nulo") Long idCliente,
                          @NotNull(message = "O id da mesa não pode ser nulo") Long idMesa) {
}
