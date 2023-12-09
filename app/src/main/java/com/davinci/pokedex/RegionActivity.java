package com.davinci.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.davinci.pokedex.adapter.RegionAdapter;
import com.davinci.pokedex.model.Region;
import com.davinci.pokedex.model.RegionList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class RegionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String name;
    RecyclerView recyclerView;
    ArrayList<RegionList> regionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        
        obtenerDatos();

        //FirebaseUser user = mAuth.getCurrentUser();
        //String name = user.getDisplayName();

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
        db.collection("Users")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //document.getData();
                            name = document.getString("Name");
                            Toast.makeText(RegionActivity.this, "BIENVENIDO MAESTRO POKEMON " + name, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });
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