package com.ziery.ReservasRestaurante.dtos;

import com.ziery.ReservasRestaurante.entites.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ClienteDto(@NotBlank(message = "O nome não pode ficar em branco")  String  nome,
                         @NotBlank(message = "O telefone não pode ficar em branco") String telefone,
                         @NotBlank(message = "O e-mail não pode ficar em branco") String email,
                         LocalDateTime dataCadastro ) {

}
