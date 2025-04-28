package com.ziery.ReservasRestaurante.dtos.request;

import jakarta.validation.constraints.NotNull;

//Classe resposável pelo recebimento de dados da mesa
public record MesaDto(@NotNull(message = "O número da mesa não pode ser nulo") Integer numero,
                      @NotNull(message = "A capacidade da mesa não pode ser nula") Integer capacidade) {

}
