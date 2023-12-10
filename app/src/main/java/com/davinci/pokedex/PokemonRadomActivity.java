package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davinci.pokedex.adapter.Pokemon_adapter;
import com.davinci.pokedex.controller.GetRandom;
import com.davinci.pokedex.model.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonRadomActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

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

        Pokemon_adapter recyclerview_adapter = new Pokemon_adapter(response,getApplicationContext(),"random");
        recyclerView.setAdapter(recyclerview_adapter);
    }

    private List<Pokemon> callApiPokemon() throws JsonProcessingException, ExecutionException, InterruptedException {
        GetRandom getRandom = new GetRandom();
        List<Pokemon> respuesta1 = getRandom.execute(URL_BASE + "pokemon/random?limit=10").get();
        return respuesta1;
    }

}
