package com.davinci.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.davinci.pokedex.PokemonActivity;
import com.davinci.pokedex.R;
import com.davinci.pokedex.RegionActivity;
import com.davinci.pokedex.model.Pokemon;

import java.util.List;
import java.util.Map;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    private final Map<String, String> mapaPokemonUser;
    private List<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(Map<String, String> mapaPokemonUser, List<Pokemon> pokemonList, Context context) {
        this.mapaPokemonUser = mapaPokemonUser;
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_card,parent,false);
        return new PokemonListAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull PokemonListAdapter.ViewHolder holder, int position) {
        //se inserta en la cardview
        //holder.imageView.setImageResource(recyclerview_lists.get(position).getImage());
        Glide.with(context).load(pokemonList.get(position).getSprites().getFront_default()).into(holder.imageView);

        int idPokemon = pokemonList.get(position).getNo();
        if (idPokemon == 49){
            String h = "";
        }
        String capturado = mapaPokemonUser.get(String.valueOf(idPokemon));

        if (capturado == null) {
            float alphaValue = 0.5f; // Puedes ajustar este valor según sea necesario
            holder.cardView.setAlpha(alphaValue);
        }else {
            String t = "";
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (capturado == null) {
                    Toast.makeText(context, "CAPTURALO PARA VER INFO", Toast.LENGTH_SHORT).show();
                } else {
                    int adapterPosition = holder.getAdapterPosition();

                    // Aquí puedes realizar acciones con el dato específico, por ejemplo, abrir una nueva actividad o fragmento.
                    Intent intent = new Intent(view.getContext(), PokemonActivity.class);
                    intent.putExtra("id_pokemon", pokemonList.get(adapterPosition).no);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //desde aca se obtiene lo que esta en pantalla
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.pokemonListCard);

        }
    }
}
