package com.dentistry.ahmed.orderonline;

import android.arch.lifecycle.ViewModelProviders;
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
import com.dentistry.ahmed.orderonline.ViewModel.OrdersViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Orders extends AppCompatActivity{


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefresh;

    private OrdersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        initViews();


        viewModel = ViewModelProviders.of(Orders.this).get(OrdersViewModel.class);
        viewModel.setSwipeToRefresh(swipeRefresh);
        viewModel.getAllOrders(this,recyclerView,swipeRefresh);


        floatingActionButtonClickListener(viewModel);


    }



    private void initViews() {
        fab = findViewById(R.id.fab);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.orders_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Orders.this));
    }



    private void floatingActionButtonClickListener(final OrdersViewModel viewModel) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModel.fabButtonStartNewOrderActivity(Orders.this);

            }
        });
    }




}
