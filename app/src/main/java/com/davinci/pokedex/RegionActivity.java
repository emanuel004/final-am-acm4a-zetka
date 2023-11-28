package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.controller.CallApi;
import com.davinci.pokedex.controller.GetPokemon;
import com.davinci.pokedex.model.Region;
import com.davinci.pokedex.model.RegionList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class RegionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Region> regions;
    ArrayList<RegionList> regionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        Toast.makeText(RegionActivity.this, "BIENVENIDO MAESTRO POKEMON", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        regionArrayList = new ArrayList<RegionList>();
        regionArrayList.add(new RegionList(R.drawable.kanto,"kanto", 1, 1, 150));
        regionArrayList.add(new RegionList(R.drawable.johto,"jhoto", 2, 152, 99));
        regionArrayList.add(new RegionList(R.drawable.hoenn,"hoenn", 3, 253, 134));
        regionArrayList.add(new RegionList(R.drawable.sinnoh,"sinnoh", 4, 387, 106));

        RegionAdapter regionAdapter = new RegionAdapter(regionArrayList,this);
        recyclerView.setAdapter(regionAdapter);
        //callApiRegion();
        //onClick(regionArrayList);
        //callApi();

    }

    /*private void callApi() {
        GetPokemon getPokemon = new GetPokemon();
        getPokemon.execute("https://pokedex-tiagoramirez.vercel.app/api/v1/pokemon?offset=150&limit=30");
    }*/

    /*private void callApiRegion(){
        CallApi callApi = new CallApi();

        Call<ArrayList<Region>> call = callApi
                .InstanceRetrofit()
                .getRegion();

        call.enqueue(new Callback<ArrayList<Region>>() {
            @Override
            public void onResponse(Call<ArrayList<Region>> call, Response<ArrayList<Region>> response) {
                if (response.isSuccessful()) {
                    List<Region> regions =  response.body();
                    //initRVAdapter(regions);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Toast.makeText(RegionActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private void initRVAdapter(List<Region> regions) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(regions);
        RegionAdapter regionAdapter = new RegionAdapter(regions,getApplicationContext());
        recyclerView.setAdapter(regionAdapter);
    }*/
}