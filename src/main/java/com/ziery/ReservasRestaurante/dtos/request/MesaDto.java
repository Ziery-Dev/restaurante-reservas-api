package com.ziery.ReservasRestaurante.dtos.request;

import jakarta.validation.constraints.NotNull;

public record MesaDto(@NotNull(message = "O número da mesa não pode ser nulo") Integer numero,
                      @NotNull(message = "A capacidade da mesa não pode ser nula") Integer capacidade) {

}
