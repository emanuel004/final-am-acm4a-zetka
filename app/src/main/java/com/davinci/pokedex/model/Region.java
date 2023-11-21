package com.davinci.pokedex.model;

public class Region {
    private String name;
    private long[] pokemons;
    private long numberOfPokemons;

    public String getName() { return name; }

    public void setName(String value) { this.name = value; }


    public long[] getPokemons() { return pokemons; }

    public void setPokemons(long[] value) { this.pokemons = value; }


    public long getNumberOfPokemons() { return numberOfPokemons; }

    public void setNumberOfPokemons(long value) { this.numberOfPokemons = value; }
}
