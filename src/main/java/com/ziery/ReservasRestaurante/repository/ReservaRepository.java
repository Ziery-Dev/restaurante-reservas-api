package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    boolean existsByMesaId(Long mesaId);
    boolean existsByClienteId(Long clienteId);
    List<Reserva> findByMesaId(Long mesaId);
    List<Reserva> findByClienteId(Long clienteId);


}
