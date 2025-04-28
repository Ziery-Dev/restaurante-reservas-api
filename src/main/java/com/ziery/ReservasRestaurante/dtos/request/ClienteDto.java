package com.ziery.ReservasRestaurante.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//classe responsável pelo recebimento de dados do cliente
public record ClienteDto(@NotBlank(message = "O nome não pode ficar em branco")  String  nome,
                         @NotBlank(message = "O telefone não pode ficar em branco") @Pattern(regexp = "^\\d{8,15}$", message = "O telefone deve conter entre 8 e 15 dígitos numéricos.")
                         String telefone,
                         @NotBlank(message = "O e-mail não pode ficar em branco") @Email String email) {

}
