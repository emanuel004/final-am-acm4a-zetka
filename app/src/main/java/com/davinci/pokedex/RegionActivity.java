package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RegionActivity extends AppCompatActivity {
    ArrayList<recyclerview_list> recyclerview_lists;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerview_lists = new ArrayList<recyclerview_list>();
        //IMAGENES
        recyclerview_lists.add(new recyclerview_list(R.drawable.kanto,"kanto"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.kanto,"k"));
        recyclerview_lists.add(new recyclerview_list(R.drawable.kanto,"j"));

        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(recyclerview_lists,this);
        recyclerView.setAdapter(recyclerview_adapter);

    }
}