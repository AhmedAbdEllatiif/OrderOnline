package com.dentistry.ahmed.orderonline.ViewModel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.NewOrderActivity;
import com.dentistry.ahmed.orderonline.Orders;
import com.dentistry.ahmed.orderonline.ProfileActivity;
import com.dentistry.ahmed.orderonline.Repository.MyRepository;

import java.util.ArrayList;
import java.util.List;



public class MainActivityViewModel extends AndroidViewModel {

    private MyRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new MyRepository();
    }


    public void startProfileActivity(Activity activity){
        activity.startActivity(new Intent(activity,ProfileActivity.class));
    }

    public void startNewOrderActivity(Activity activity){
        Intent intent = new Intent(activity,NewOrderActivity.class);
        activity.startActivityForResult(intent,2);

    }

    public void startOrdersActivity(Activity activity){
        Intent intent = new Intent(activity, Orders.class);
        activity.startActivity(intent);

    }


    public void setRecyclerViewDeals(Activity activity, RecyclerView recyclerView){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);


         List<Deals> dealsList = new ArrayList<>();

        repository.getAllDeals(activity,recyclerView, dealsList);


    }


}
