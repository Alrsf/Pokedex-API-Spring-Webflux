package com.pokedex.pokedex;

import com.pokedex.pokedex.model.Pokemon;
import com.pokedex.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args); }

		@Bean
	CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository) {
		return args -> {
			Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon("01", "Venusaur", "Kanto", "Planta", 100.9, 2.0),
					new Pokemon("157", "Typhlosion", "Johto", "Fogo", 79.5, 1.7),
					new Pokemon("260", "Swampert", "Hoenn", "Água", 81.9, 1.5),
					new Pokemon("389", "Torterra", "Sinnoh", "Planta", 310.0, 2.2),
					new Pokemon("500", "Emboar", "Unova", "Fogo", 150.5, 1.6),
					new Pokemon("658", "Greninja", "Kalos", "Água", 40.6, 1.5))
					.flatMap(repository::save);

				pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);

		};
	}
}
