package com.wildcodeschool.milkshake;

import com.wildcodeschool.milkshake.entity.Recette;
import com.wildcodeschool.milkshake.repository.RecetteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MilkshakeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MilkshakeApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedData(RecetteRepository repo) {
		return args -> {

			repo.save(new Recette("Strawberry marshmallow", 15, "strawberry"));

			repo.save(new Recette("Cool mint", 5, "mint"));

			repo.save(new Recette("Blueberry cheesecake", 30, "blueberry"));

			repo.save(new Recette("Triple nut caramel", 20, "caramel"));
		};
	}
}
