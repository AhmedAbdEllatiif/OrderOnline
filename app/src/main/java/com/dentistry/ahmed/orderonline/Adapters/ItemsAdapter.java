package com.dentistry.ahmed.orderonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dentistry.ahmed.orderonline.Model.Item;
import com.dentistry.ahmed.orderonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private List<Item> itemsList;

    public ItemsAdapter(Context context, List<Item> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_image,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = itemsList.get(position);

        Picasso.get().load(item.getImage()).into(holder.item_image);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_image;

        public ViewHolder(View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
        }
    }
}
