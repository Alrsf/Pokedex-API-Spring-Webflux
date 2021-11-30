package com.pokedex.pokedex.controller;

import com.pokedex.pokedex.model.Pokemon;
import com.pokedex.pokedex.model.PokemonEvent;
import com.pokedex.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

@RestController
@RequestMapping("/pokemons")

public class PokemonController {

    private PokemonRepository repository;
    public PokemonController(PokemonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons() {
        return repository.findAll();
    }

    @GetMapping("/{dex}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String dex) {
        return repository.findById(dex)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return repository.save(pokemon);
    }

    @PutMapping("{dex}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "dex") String dex,
                                                       @RequestBody Pokemon pokemon) {
        return repository.findById(dex)
                .flatMap(existingPokemon -> {
                    existingPokemon.setNome(pokemon.getNome());
                    existingPokemon.setRegiao(pokemon.getRegiao());
                    existingPokemon.setTipo(pokemon.getTipo());
                    existingPokemon.setPeso(pokemon.getPeso());
                    existingPokemon.setAltura(pokemon.getAltura());
                    return repository.save(existingPokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{dex}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "dex") String dex) {
        return repository.findById(dex)
                .flatMap(existingPokemon ->
                            repository.delete(existingPokemon)
                                    .then(Mono.just(ResponseEntity.ok().<Void>build())))
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemons() {
        return repository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PokemonEvent(val, "Pokemondex"));
    }

}
