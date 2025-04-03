package com.ziery.ReservasRestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //Usado junto a anotação @EntityListeners nas Entidades
public class ReservasRestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasRestauranteApplication.class, args);
	}

}
