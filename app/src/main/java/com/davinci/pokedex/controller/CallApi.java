package com.davinci.pokedex.controller;

import static com.davinci.pokedex.Constants.URL_BASE;
import com.davinci.pokedex.interfaces.PokemonService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CallApi {
    public PokemonService InstanceRetrofit(){
        // Crea una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService pokemonService = retrofit.create(PokemonService.class);

        return pokemonService;
    }
}
