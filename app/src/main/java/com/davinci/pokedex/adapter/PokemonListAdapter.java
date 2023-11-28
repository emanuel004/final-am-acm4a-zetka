package com.davinci.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davinci.pokedex.R;
import com.davinci.pokedex.model.Pokemon;

import java.util.List;

public class PokemonListAdapter {
    private List<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card,parent,false);
        return new PokemonListAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull PokemonListAdapter.ViewHolder holder, int position) {
        //se inserta en la cardview
        //holder.imageView.setImageResource(recyclerview_lists.get(position).getImage());
        Glide.with(context).load(pokemonList.get(position).getSprites().getFront_default()).into(holder.imageView);
        holder.textView.setText(pokemonList.get(position).getName());
    }
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //desde aca se obtiene lo que esta en pantalla
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
