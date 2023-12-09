package com.davinci.pokedex;

import static com.davinci.pokedex.Constants.URL_BASE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.davinci.pokedex.adapter.PokemonListAdapter;
import com.davinci.pokedex.controller.GetPokemon;
import com.davinci.pokedex.model.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PokemonListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    RecyclerView recyclerView;
    int inicio;
    int total;

    Map<String, String> mapaPokemonUser = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        obtenerDatos();

        Intent intent = getIntent();
        inicio = intent.getIntExtra("inicio", 0);
        total = intent.getIntExtra("total", 0);

        recyclerView = findViewById(R.id.pokemonView);
        recyclerView.setHasFixedSize(true);
        //formato lista o GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        List<Pokemon> response;
        try {
            response = callApiPokemon();
        } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        PokemonListAdapter pokemons = new PokemonListAdapter(mapaPokemonUser, response, getApplicationContext());
        recyclerView.setAdapter(pokemons);

    }

    private List<Pokemon> callApiPokemon() throws JsonProcessingException, ExecutionException, InterruptedException {
        GetPokemon getPokemon = new GetPokemon();
        return getPokemon.execute(URL_BASE + "pokemon?offset= "+inicio + "&limit=" + total).get();

    }

    private void obtenerDatos() {
        db.collection("user_pokedex")
                .whereEqualTo("uid", mAuth.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mapaPokemonUser.put(document.getString("n_pokedex"),"");
                            }
                        }
                    }
                });
    }


}