package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
