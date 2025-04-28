package com.ziery.ReservasRestaurante.utils.cliente;

import com.ziery.ReservasRestaurante.entites.Cliente;

import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
//Contém métodos de validação auxiliares para o clienteService
@Component
@AllArgsConstructor
public class ClienteValidador {

    private final ClienteRepository clienteRepository;

    public void verificarEmailETelefone(Long id, String telefone, String email) {
        Optional<Cliente> clientePorTelefone = clienteRepository.findByTelefone(telefone);
        Optional<Cliente> clientePorEmail = clienteRepository.findByEmail(email);

        boolean telefoneJaExiste = clientePorTelefone.isPresent();
        boolean emailJaExiste = clientePorEmail.isPresent();

        boolean telefoneEmUso = telefoneJaExiste && (id == null || !clientePorTelefone.get().getId().equals(id));
        boolean emailEmUso = emailJaExiste && (id == null || !clientePorEmail.get().getId().equals(id));

        if (telefoneEmUso && emailEmUso) {
            throw new ViolacaoDeIntegridadeException("O telefone: " + telefone + " E o email " + email + " já estão em uso!");
        }
        if (telefoneEmUso) {
            throw new ViolacaoDeIntegridadeException("O telefone: " + telefone + " já está em uso!");
        }
        if (emailEmUso) {
            throw new ViolacaoDeIntegridadeException("O email: " + email + " já está em uso!");
        }
    }

}
