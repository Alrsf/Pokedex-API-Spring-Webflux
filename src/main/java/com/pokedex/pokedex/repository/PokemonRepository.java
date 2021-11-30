package com.pokedex.pokedex.repository;

import com.pokedex.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository <Pokemon, String> {}
