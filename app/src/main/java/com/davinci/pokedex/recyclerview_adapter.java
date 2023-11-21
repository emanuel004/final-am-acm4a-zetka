package com.davinci.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davinci.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class recyclerview_adapter extends RecyclerView.Adapter<recyclerview_adapter.ViewHolder> {

    private List<Pokemon> pokemonList;
    private Context context;

    public recyclerview_adapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public recyclerview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerview_adapter.ViewHolder holder, int position) {
        //se inserta en la cardview
        //holder.imageView.setImageResource(recyclerview_lists.get(position).getImage());
        Glide.with(context).load(pokemonList.get(position).getSprites().getFront_default()).into(holder.imageView);

        holder.textView.setText(pokemonList.get(position).getName());
    }

    @Override
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
