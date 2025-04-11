package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByTelefone(String telefone);
    boolean existsByEmail(String email);


    Optional<Cliente> findByTelefone(String telefone);
    Optional<Cliente> findByEmail(String email);
}
