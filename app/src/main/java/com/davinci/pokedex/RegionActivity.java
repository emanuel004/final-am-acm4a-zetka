package com.davinci.pokedex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.controller.ObtenerDatos;
import com.davinci.pokedex.model.RegionList;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class RegionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String name;
    RecyclerView recyclerView;
    ArrayList<RegionList> regionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        
        obtenerDatos();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        regionArrayList = new ArrayList<RegionList>();
        regionArrayList.add(new RegionList(R.drawable.kanto,"Kanto", 1, 1, 150));
        regionArrayList.add(new RegionList(R.drawable.johto,"Jhoto", 2, 151, 99));
        regionArrayList.add(new RegionList(R.drawable.hoenn,"Hoenn", 3, 251, 134));
        regionArrayList.add(new RegionList(R.drawable.sinnoh,"Sinnoh", 4, 386, 106));

        RegionAdapter regionAdapter = new RegionAdapter(regionArrayList,this);
        recyclerView.setAdapter(regionAdapter);

    }

    private void obtenerDatos() {
        ObtenerDatos obtenerDatos = new ObtenerDatos("Users");
        obtenerDatos.obtenerNameLogin();
        name = obtenerDatos.getName();
        Toast.makeText(RegionActivity.this, "BIENVENIDO MAESTRO POKEMON " + name, Toast.LENGTH_SHORT).show();
    }

    public void salir(View view){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }


    public void onClickRandom(View view) {
        Intent intent = new Intent(view.getContext(), PokemonRadomActivity.class);
        startActivity(intent);
    }
}