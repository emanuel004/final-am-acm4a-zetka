package com.davinci.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.davinci.pokedex.R;
import com.davinci.pokedex.model.Region;

import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {
    private List<Region> regionList;
    private Context context;

    public RegionAdapter(List<Region> regions, Context applicationContext) {
        this.regionList = regionList;
        this.context = context;
    }


    @NonNull
    @Override
    public RegionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionAdapter.ViewHolder holder, int position) {
        //se inserta en la cardview
        String nameImage = regionList.get(position).getName()+ ".png";
        int idImage = context
                .getApplicationContext()
                .getResources()
                .getIdentifier(nameImage,"drawable", context.getPackageName());
        holder.imageView.setImageResource(idImage);
        //Glide.with(context).load(regionList.get(position).getSprites().getFront_default()).into(holder.imageView);

        holder.textView.setText(regionList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
