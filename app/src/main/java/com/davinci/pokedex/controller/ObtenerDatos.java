package com.davinci.pokedex.controller;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.davinci.pokedex.RegionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ObtenerDatos {
    private String collection;
    Map<Object, String> mapaPokemonUser = new HashMap<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();;

    public String getName() {
        return name;
    }

    private String name;

    public ObtenerDatos(String collection) {
        this.collection = collection;
    }

    public Map<Object, String> obtenerDatos() {
        db.collection(collection)
                .whereEqualTo("uid", mAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mapaPokemonUser.put(document.get("pokedex"),"");
                            }
                        }
                    }
                });
        return mapaPokemonUser;
    }

    public void obtenerNameLogin() {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name = document.getString("Name");
                            }
                        }
                    }
                });
    }
}
