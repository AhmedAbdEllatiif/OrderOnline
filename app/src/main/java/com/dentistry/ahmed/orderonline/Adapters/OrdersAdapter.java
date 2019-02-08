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

    private List<Order> orderList;
    private Context context;
    private onItemClickListener onEditClickListener;
    private onItemClickListener onDeleteClickListener;


    public OrdersAdapter(Context context,List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    public void setOnEditClickListener(onItemClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    public void setOnDeleteClickListener(onItemClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.cardview_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            final Order orderItem = orderList.get(position);

            Picasso.get().load(orderItem.getImage_URL()).into(holder.orderImage);
            holder.title.setText(orderItem.getOrderName());
            holder.txt_orderName.setText(orderItem.getOrderName());
            holder.txt_desc.setText(orderItem.getDescription());
            holder.txt_orderColor.setText(orderItem.getColor());
            holder.txt_orderQuantity.setText(String.valueOf(orderItem.getQuantity()));



            if(onEditClickListener!=null){
                holder.img_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEditClickListener.onClick(position,orderItem);
                    }
                });
            }

        if(onDeleteClickListener!=null){
            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClickListener.onClick(position,orderItem);
                }
            });
        }

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

            ImageView img_edit;
            ImageView orderImage;
            ImageView img_delete;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_orderColor = itemView.findViewById(R.id.txt_orderColor);
            txt_orderName = itemView.findViewById(R.id.txt_orderName);
            txt_orderQuantity = itemView.findViewById(R.id.txt_orderQuantity);
            orderImage = itemView.findViewById(R.id.orderImage);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);

        }
    }

    public interface onItemClickListener{
        void onClick(int position,Order order);
    }
}
