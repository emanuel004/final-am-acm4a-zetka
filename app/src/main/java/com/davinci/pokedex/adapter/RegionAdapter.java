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

import com.davinci.pokedex.PokemonListActivity;
import com.davinci.pokedex.R;
import com.davinci.pokedex.model.RegionList;

import java.util.ArrayList;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {
    private final ArrayList<RegionList> regionList;
    private final Context context;

    public RegionAdapter(ArrayList<RegionList> regions, Context applicationContext) {
        this.regionList = regions;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public RegionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.region_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(regionList.get(position).getImage());
        holder.textView.setText(regionList.get(position).getText());

        // Asignar el evento de clic a la CardView

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                // Obtener datos específicos de la CardView en esta posición
                //String datoEspecifico = listaDeDatos.get(position).getDatoEspecifico();
                //holder.

                // Aquí puedes realizar acciones con el dato específico, por ejemplo, abrir una nueva actividad o fragmento.
               Intent intent = new Intent(view.getContext(), PokemonListActivity.class);
                intent.putExtra("inicio",regionList.get(adapterPosition).getInicio());
                intent.putExtra("total",regionList.get(adapterPosition).getTotal());
                intent.putExtra("region",regionList.get(adapterPosition).getText());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //desde aca se obtiene lo que esta en pantalla
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            cardView = itemView.findViewById(R.id.idCard);
        }
    }
}
