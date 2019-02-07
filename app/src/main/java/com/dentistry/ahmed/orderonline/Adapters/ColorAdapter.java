package com.dentistry.ahmed.orderonline.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.R;
import com.squareup.picasso.Picasso;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView deals_image;

        public ViewHolder(View itemView) {
            super(itemView);

            deals_image = itemView.findViewById(R.id.deals_image);

        }
    }

}

