package com.davinci.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davinci.pokedex.PokemonActivity;
import com.davinci.pokedex.PokemonListActivity;
import com.davinci.pokedex.R;
import com.davinci.pokedex.model.Pokemon;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    private List<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(List<Pokemon> pokemonList, Context context) {
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                // Obtener datos específicos de la CardView en esta posición
                //String datoEspecifico = listaDeDatos.get(position).getDatoEspecifico();
                //holder.

                // Aquí puedes realizar acciones con el dato específico, por ejemplo, abrir una nueva actividad o fragmento.
                Intent intent = new Intent(view.getContext(), PokemonActivity.class);
                intent.putExtra("id_pokemon", pokemonList.get(adapterPosition).no);
                view.getContext().startActivity(intent);

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
