package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.davinci.pokedex.adapter.Pokemon_adapter;
import com.davinci.pokedex.controller.GetPokemon;
import com.davinci.pokedex.controller.PokemonGet;
import com.davinci.pokedex.model.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PokemonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Intent intent = getIntent();
        id = intent.getIntExtra("id_pokemon",0);

        recyclerView = findViewById(R.id.pokemon1View);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        List<Pokemon> response;
        try {
            response = callApiPokemon();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Pokemon_adapter recyclerview_adapter = new Pokemon_adapter(response,getApplicationContext());
        recyclerView.setAdapter(recyclerview_adapter);

    }

    private List<Pokemon> callApiPokemon() throws JsonProcessingException, ExecutionException, InterruptedException {
        PokemonGet getPokemon = new PokemonGet();
        Pokemon respuesta1 = getPokemon.execute(URL_BASE + "pokemon/"+id).get();
        List<Pokemon> listaDePokemones = new ArrayList<>();
        listaDePokemones.add(respuesta1);

        return listaDePokemones;
    }

}