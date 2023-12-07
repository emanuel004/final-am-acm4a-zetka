package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.controller.GetRandom;
import com.davinci.pokedex.model.Pokemon;
import com.davinci.pokedex.model.Region;
import com.davinci.pokedex.model.RegionList;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class RegionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    RecyclerView recyclerView;
    ArrayList<Region> regions;
    ArrayList<RegionList> regionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(RegionActivity.this, "BIENVENIDO MAESTRO POKEMON", Toast.LENGTH_SHORT).show();

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

        ImageButton imageButton = findViewById(R.id.random);
        clickEvent(imageButton);

    }

    private void clickEvent(ImageButton imageButton) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PokemonRadomActivity.class);
                startActivity(intent);
            }
        });

    }

    private void salir(ImageButton imageButton){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }


}