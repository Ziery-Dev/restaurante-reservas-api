package com.ziery.ReservasRestaurante.repository;

import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long>{

}
