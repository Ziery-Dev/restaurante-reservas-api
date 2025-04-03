package com.ziery.ReservasRestaurante.entites;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class) //para que funcione as anotations @CreatedDate  @LastModifiedDate
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String telefone;
    private String email;
    @CreatedDate
    private LocalDateTime dataCadastro;
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservasList;



}
