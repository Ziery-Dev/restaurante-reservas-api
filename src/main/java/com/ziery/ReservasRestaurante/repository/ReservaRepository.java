package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    boolean existsByMesaId(Long mesaId);
}
