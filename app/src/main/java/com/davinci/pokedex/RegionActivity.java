package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.model.Region;
import com.davinci.pokedex.model.RegionList;

import java.util.ArrayList;


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
        regionArrayList.add(new RegionList(R.drawable.kanto,"Kanto", 1, 1, 150));
        regionArrayList.add(new RegionList(R.drawable.johto,"Jhoto", 2, 151, 99));
        regionArrayList.add(new RegionList(R.drawable.hoenn,"Hoenn", 3, 251, 134));
        regionArrayList.add(new RegionList(R.drawable.sinnoh,"Sinnoh", 4, 386, 106));

        RegionAdapter regionAdapter = new RegionAdapter(regionArrayList,this);
        recyclerView.setAdapter(regionAdapter);

    }

}