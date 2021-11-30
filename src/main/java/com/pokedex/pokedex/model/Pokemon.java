package com.pokedex.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document
public class Pokemon {

    @Id
    private String dex; // Identificação do pokémon na PokéDex
    private String nome; // Nome do pokémon
    private String regiao; // Região onde é encontrado
    private String tipo; // Tipo do pokémon
    private Double peso; // Peso médio do pokémon
    private Double altura; // Altura média do pokémon

    public Pokemon() {
        super();
    }

    public Pokemon(String dex, String nome, String regiao, String tipo, Double peso, Double altura) {
        this.dex = dex;
        this.nome = nome;
        this.regiao = regiao;
        this.tipo = tipo;
        this.peso = peso;
        this.altura = altura;
    }

    public String getDex() {
        return dex;
    }

    public void setDex(String dex) {
        this.dex = dex;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "dex='" + dex + '\'' +
                ", nome='" + nome + '\'' +
                ", regiao='" + regiao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", peso=" + peso + '\'' +
                ", altura=" + altura +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(dex, pokemon.dex)
                && Objects.equals(nome, pokemon.nome)
                && Objects.equals(regiao, pokemon.regiao)
                && Objects.equals(tipo, pokemon.tipo)
                && Objects.equals(peso, pokemon.peso)
                && Objects.equals(altura, pokemon.altura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dex, nome, regiao, tipo, peso, altura);
    }
}
