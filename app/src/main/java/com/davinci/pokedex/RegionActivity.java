package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.controller.CallApi;
import com.davinci.pokedex.interfaces.PokemonService;
import com.davinci.pokedex.model.Pokemon;
import com.davinci.pokedex.model.Region;

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

        // Obtiene el valor de la clave "string_key"
        Integer intValue = getResources().getInteger(R.integer.random);

        Call<List<Region>> call = new CallApi()
                .InstanceRetrofit()
                .getRegion();

        call.enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                if (response.isSuccessful()) {
                    List<Region> regions =  response.body();
                    initRVAdapter(regions);
                }
            }

            @Override
            public void onFailure(Call<List<Region>> call, Throwable t) {
                Toast.makeText(RegionActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRVAdapter(List<Region> regions) {
        RegionAdapter regionAdapter = new RegionAdapter(regions,getApplicationContext());
        recyclerView.setAdapter(regionAdapter);
    }
}