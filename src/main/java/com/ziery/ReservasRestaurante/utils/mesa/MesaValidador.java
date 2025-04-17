package com.ziery.ReservasRestaurante.utils.mesa;

import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class MesaValidador {

    private final MesaRepository mesaRepository;

    public void validarNumeroMesa(int numero, Long idAtual) {
        Optional<Mesa> mesaExistente = mesaRepository.findByNumero(numero);

        if (mesaExistente.isPresent()) {
            // Se for atualização, verifica se o ID é diferente
            if (idAtual == null || !mesaExistente.get().getId().equals(idAtual)) {
                throw new ViolacaoDeIntegridadeException("O número: " + numero + " já está em uso por outra mesa!");
            }
        }
    }

}
