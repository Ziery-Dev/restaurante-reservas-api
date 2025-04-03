package com.ziery.ReservasRestaurante.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private int capacidade;

    @OneToMany(mappedBy = "mesa")
    private List<Reserva> reservas;




}
