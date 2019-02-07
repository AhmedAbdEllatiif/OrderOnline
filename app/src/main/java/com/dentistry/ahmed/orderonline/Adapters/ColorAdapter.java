package com.dentistry.ahmed.orderonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dentistry.ahmed.orderonline.Model.Color;
import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{

    private Context context;
    private List<Color> colorList;

    public ColorAdapter(Context context, List<Color> colorList) {
        this.context = context;
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_color,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Color colorItem = colorList.get(position);

        Picasso.get().load(colorItem.getURl()).into(holder.color_image);

    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView color_image;

        public ViewHolder(View itemView) {
            super(itemView);

            color_image = itemView.findViewById(R.id.color_image);

        }
    }

}

