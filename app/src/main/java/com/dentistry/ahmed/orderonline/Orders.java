package com.dentistry.ahmed.orderonline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.Adapters.OrdersAdapter;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Orders extends AppCompatActivity{

    private List<Order> orderList;
    private OrdersAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.orders_recyclerView);
        fab = findViewById(R.id.fab);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getDate();

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefresh.setRefreshing(false);

                        getDate();

                    }
                },3000);
            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Orders.this,NewOrderActivity.class);
                startActivityForResult(intent,20);

            }
        });






    }

    private void getDate(){

        swipeRefresh.setRefreshing(true);
        orderList = new ArrayList<>();
        MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        //To refresh the recyclerView
                        getDate();
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
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Orders.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
