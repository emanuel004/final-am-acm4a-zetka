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
import com.davinci.pokedex.controller.Capturar;
import com.davinci.pokedex.controller.ObtenerDatos;
import com.davinci.pokedex.model.Pokemon;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon_adapter extends RecyclerView.Adapter<Pokemon_adapter.ViewHolder> {

    private final List<Pokemon> pokemonList;
    private final Context context;
    private final String views;
    Map<Object, String> mapaPokemonUser = new ObtenerDatos("user_pokedex").obtenerDatos();


    public Pokemon_adapter(List<Pokemon> pokemonList, Context context, String views) {
        this.pokemonList = pokemonList;
        this.context = context;
        this.views = views;
    }

    @NonNull
    @Override
    public Pokemon_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pokemon_adapter.ViewHolder holder, int position) {
        //se inserta en la cardview
        //holder.imageView.setImageResource(recyclerview_lists.get(position).getImage());
        Glide.with(context).load(pokemonList.get(position).getSprites().getFront_default()).into(holder.imageView);
        holder.textView.setText(pokemonList.get(position).getName());

        holder.idText.setText("Pokedex: " + pokemonList.get(position).getNo());

        holder.type.setText("Types: ");
        pokemonList.get(position).getTypes().forEach(typeValue -> holder.type.append(typeValue + ". "));

        holder.region.setText("Regions: ");
        pokemonList.get(position).getRegions().forEach(typeValue -> holder.region.append(typeValue + ". "));

        int idPokemon = pokemonList.get(position).getNo();

        if (views.equals("random")){

            if (mapaPokemonUser.get("pokedex") == null){
                holder.bottom.setVisibility(View.VISIBLE);
                holder.captured.setVisibility(View.INVISIBLE);
            }else{
                holder.bottom.setVisibility(View.INVISIBLE);
                holder.captured.setVisibility(View.VISIBLE);
            }
            holder.bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Capturar capturar = new Capturar(idPokemon, pokemonList.get(holder.getAdapterPosition()).getName());
                    capturar.insertarPokemon();
                    holder.bottom.setVisibility(View.INVISIBLE);
                    holder.captured.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView idText;
        TextView type;
        TextView region;
        TextView captured;
        MaterialButton bottom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //desde aca se obtiene lo que esta en pantalla
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            bottom = itemView.findViewById(R.id.captura);
            captured = itemView.findViewById(R.id.captured);

            idText = itemView.findViewById(R.id.nPokedex);
            type = itemView.findViewById(R.id.types);
            region = itemView.findViewById(R.id.regions);
        }
    }


}
