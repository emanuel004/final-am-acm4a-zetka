package com.davinci.pokedex.interfaces;

import androidx.annotation.Nullable;

import com.davinci.pokedex.model.Pokemon;
import com.davinci.pokedex.model.Region;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface PokemonService {
   @POST("/pokemon")
    Call<ArrayList<Pokemon>> getPokemon(@Nullable @Query("offset") Integer offset, @Nullable @Query("limit") Integer limit);

    @POST("pokemon/random")
    Call<ArrayList<Pokemon>> getPokemonRandom(@Nullable @Query("limit") Integer limit);

    @GET("region")
    Call<ArrayList<Region>> getRegion();

}
