package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.davinci.pokedex.adapter.PokemonListAdapter;
import com.davinci.pokedex.adapter.recyclerview_adapter;
import com.davinci.pokedex.controller.GetPokemon;
import com.davinci.pokedex.model.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int inicio;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        Intent intent = getIntent();
        inicio = intent.getIntExtra("inicio", 0);
        total = intent.getIntExtra("total", 0);

        recyclerView = findViewById(R.id.pokemonView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        List<Pokemon> response;
        try {
            response = callApiPokemon();
        } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        PokemonListAdapter pokemons = new PokemonListAdapter(response, getApplicationContext());
        recyclerView.setAdapter(pokemons);

    }

    private List<Pokemon> callApiPokemon() throws JsonProcessingException, ExecutionException, InterruptedException {
        GetPokemon getPokemon = new GetPokemon();
        List<Pokemon> respuesta1 = getPokemon.execute(URL_BASE + "pokemon?offset= "+inicio + "&limit=" + total).get();
        return respuesta1;
    }
}