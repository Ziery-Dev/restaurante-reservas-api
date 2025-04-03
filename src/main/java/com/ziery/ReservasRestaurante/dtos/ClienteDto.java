package com.ziery.ReservasRestaurante.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(@NotBlank(message = "O nome não pode ficar em branco")  String  nome,
                         @NotBlank(message = "O telefone não pode ficar em branco") String telefone,
                         @NotBlank(message = "O e-mail não pode ficar em branco") String email) {

}
