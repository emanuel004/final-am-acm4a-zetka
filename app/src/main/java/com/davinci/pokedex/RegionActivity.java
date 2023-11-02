package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.davinci.pokedex.controller.CallApi;
import com.davinci.pokedex.interfaces.PokemonService;
import com.davinci.pokedex.model.Pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callApiPokemon();
    }

    private void callApiPokemon() {
        CallApi callApi = new CallApi();
        PokemonService pokemonService = callApi.InstanceRetrofit()
                .create(PokemonService.class);

        // Obtiene el valor de la clave "string_key"
        Integer intValue = getResources().getInteger(R.integer.random);

        Call<ArrayList<Pokemon>> call = pokemonService.getPokemonRandom(intValue);

        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                if (response.isSuccessful()) {
                    List<Pokemon> pokemons =  response.body();
                    Toast.makeText(RegionActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    initRVAdapter(pokemons);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Toast.makeText(RegionActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRVAdapter(List<Pokemon> pokemons) {
        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(pokemons,getApplicationContext());
        recyclerView.setAdapter(recyclerview_adapter);
    }
}