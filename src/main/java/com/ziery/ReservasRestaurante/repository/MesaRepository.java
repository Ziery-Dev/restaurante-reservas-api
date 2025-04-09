package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long>{
    boolean existsByNumero(int numero);

    Optional<Mesa> findByNumero(int numero);
}
