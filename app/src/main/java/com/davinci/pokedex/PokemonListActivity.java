package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.davinci.pokedex.adapter.recyclerview_adapter;
import com.davinci.pokedex.controller.CallApi;
import com.davinci.pokedex.controller.GetPokemon;
import com.davinci.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int inicio;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Intent intent = getIntent();
        inicio = intent.getIntExtra("inicio",0);
        total = intent.getIntExtra("total",0);

        recyclerView = findViewById(R.id.pokemonView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        callApiPokemon();

    }

    private void callApiPokemon() {
        GetPokemon getPokemon = new GetPokemon();
        getPokemon.execute(URL_BASE + "pokemon?offset= "+inicio + "&limit=" + total);
    }
}