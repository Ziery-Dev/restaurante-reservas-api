package com.ziery.ReservasRestaurante.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int numero;
    private int capacidade;

    @OneToMany(mappedBy = "mesa")
    private List<Reserva> reservas;

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                '}';
    }
}
