package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.davinci.pokedex.controller.CallApi;
import com.davinci.pokedex.interfaces.PokemonService;
import com.davinci.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        recyclerView = findViewById(R.id.pokemonView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        callApiPokemon();

    }

    private void callApiPokemon() {
        CallApi callApi = new CallApi();

        Call<ArrayList<Pokemon>> call = callApi.InstanceRetrofit().getPokemonRandom(10);

        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                if (response.isSuccessful()) {
                    List<Pokemon> pokemons =  response.body();
                    Toast.makeText(PokemonActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    initRVAdapter(pokemons);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Toast.makeText(PokemonActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRVAdapter(List<Pokemon> pokemons) {
        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(pokemons,getApplicationContext());
        recyclerView.setAdapter(recyclerview_adapter);
    }
}