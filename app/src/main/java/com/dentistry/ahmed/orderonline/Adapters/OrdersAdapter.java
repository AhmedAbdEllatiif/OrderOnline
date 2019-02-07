package com.dentistry.ahmed.orderonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dentistry.ahmed.orderonline.Model.Order;
import com.dentistry.ahmed.orderonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    List<Order> orderList;
    Context context;

    public OrdersAdapter(Context context,List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.cardview_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Order orderItem = orderList.get(position);

            Picasso.get().load(orderItem.getImage_URL()).into(holder.orderImage);
            holder.title.setText(orderItem.getOrderName());
            holder.txt_orderName.setText(orderItem.getOrderName());
            holder.txt_desc.setText(orderItem.getDescription());
            holder.txt_orderColor.setText(orderItem.getColor());
            holder.txt_orderQuantity.setText(String.valueOf(orderItem.getQuantity()));



    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView title;
            TextView txt_desc;
            TextView txt_orderColor;
            TextView txt_orderName;
            TextView txt_orderQuantity;

            ImageView orderImage;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_orderColor = itemView.findViewById(R.id.txt_orderColor);
            txt_orderName = itemView.findViewById(R.id.txt_orderName);
            txt_orderQuantity = itemView.findViewById(R.id.txt_orderQuantity);
            orderImage = itemView.findViewById(R.id.orderImage);

        }
    }
}
