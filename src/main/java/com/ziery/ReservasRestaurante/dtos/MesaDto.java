package com.ziery.ReservasRestaurante.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MesaDto(@NotNull(message = "O número da mesa não pode ser nulo") Integer numero,
                      @NotNull(message = "A capacidade da mesa não pode ser nula") Integer capacidade) {

}
