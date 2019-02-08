package com.dentistry.ahmed.orderonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dentistry.ahmed.orderonline.Adapters.OrdersAdapter;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Orders extends AppCompatActivity {

    private List<Order> orderList;
    private OrdersAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.orders_recyclerView);
        fab = findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Orders.this,NewOrderActivity.class);
                startActivityForResult(intent,20);

            }
        });



        orderList = new ArrayList<>();
        MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                   Order order = snapshot.getValue(Order.class);
                   orderList.add(order);
                }
                adapter = new OrdersAdapter(Orders.this,orderList);
                recyclerView.setAdapter(adapter);

                adapter.setOnDeleteClickListener(new OrdersAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position, Order order) {
                        MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid())
                                .child(order.getId())
                                .removeValue();
                    }
                });

                adapter.setOnEditClickListener(new OrdersAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position, Order order) {

                        Intent intent = new Intent(Orders.this,NewOrderActivity.class);
                        intent.putExtra("imageURL",order.getImage_URL());
                        intent.putExtra("color",order.getColor());
                        intent.putExtra("quantity",order.getQuantity());
                        intent.putExtra("OrderID",order.getId());
                        intent.putExtra("Ahmed","1");


                        startActivityForResult(intent,20);

                    }



                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
