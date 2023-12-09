package com.davinci.pokedex.controller;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Capturar {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String uid = mAuth.getUid();
    private String pokedex;

    public Capturar(String pokedex) {
        this.pokedex = pokedex;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void insertarPokemon(){
        Map<String, String> pokedexAB = new HashMap<>();
        pokedexAB.put("n_pokedex", pokedex);
        pokedexAB.put("uid", uid);

        db.collection("user_pokedex")
                .add(pokedexAB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



}
