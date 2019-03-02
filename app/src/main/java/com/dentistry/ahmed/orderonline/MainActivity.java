package com.dentistry.ahmed.orderonline;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dentistry.ahmed.orderonline.Adapters.DealsAdapters;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.ViewModel.MainActivityViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

private RecyclerView recyclerView;
private DealsAdapters adapter;
private List<Deals> dealsList;
private ImageView img_profile;
private ImageView img_newOrder;
private ImageView img_trackOrder;
private MainActivityViewModel viewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        MyonClickListeners();

        setRecyclerViewDeals();


    }


    private void initViews() {
        recyclerView = findViewById(R.id.deals_recyclerView);
        img_profile = findViewById(R.id.img_profile);
        img_newOrder = findViewById(R.id.img_newOrder);
        img_trackOrder = findViewById(R.id.img_trackOrder);
    }

    private void MyonClickListeners() {
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              viewModel.startProfileActivity(MainActivity.this);

            }
        });
        img_newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              viewModel.startNewOrderActivity(MainActivity.this);

            }
        });


        img_trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.startOrdersActivity(MainActivity.this);

            }
        });
    }

    private void setRecyclerViewDeals() {

        viewModel.setRecyclerViewDeals(this,recyclerView);

    }

}
