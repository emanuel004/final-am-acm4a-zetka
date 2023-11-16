package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

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

        Toast.makeText(RegionActivity.this, "BIENVENIDO MAESTRO POKEMON", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callApiPokemon();
    }


    private void callApiPokemon() {
        CallApi callApi = new CallApi();

        Call<ArrayList<Region>> call = callApi
                .InstanceRetrofit()
                .getRegion();

        call.enqueue(new Callback<ArrayList<Region>>() {
            @Override
            public void onResponse(Call<ArrayList<Region>> call, Response<ArrayList<Region>> response) {
                if (response.isSuccessful()) {
                    List<Region> regions =  response.body();
                    initRVAdapter(regions);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Toast.makeText(RegionActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRVAdapter(List<Region> regions) {
        RegionAdapter regionAdapter = new RegionAdapter(regions,getApplicationContext());
        recyclerView.setAdapter(regionAdapter);
    }
}