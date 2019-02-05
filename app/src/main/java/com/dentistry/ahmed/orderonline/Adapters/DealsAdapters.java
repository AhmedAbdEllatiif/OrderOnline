package com.dentistry.ahmed.orderonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.R;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealsAdapters extends RecyclerView.Adapter<DealsAdapters.ViewHolder> {

    private List<Deals> dealsList;
    private Context context;

    public DealsAdapters(Context context, List<Deals> dealsList) {
        this.dealsList = dealsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_deals,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Deals deals = dealsList.get(position);

        Picasso.get().load(deals.getURL()).into(holder.deals_image);

    }

    @Override
    public int getItemCount() {
        return dealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView deals_image;

        public ViewHolder(View itemView) {
            super(itemView);

            deals_image = itemView.findViewById(R.id.deals_image);

        }
    }

}
