package com.dentistry.ahmed.orderonline.ViewModel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.Adapters.OrdersAdapter;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Order;
import com.dentistry.ahmed.orderonline.NewOrderActivity;
import com.dentistry.ahmed.orderonline.Orders;
import com.dentistry.ahmed.orderonline.R;
import com.dentistry.ahmed.orderonline.Repository.MyRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersViewModel extends AndroidViewModel {

    private List<Order> orderList = new ArrayList<>();
    private OrdersAdapter adapter;
    private MyRepository repository;

    public OrdersViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository();
}



    public void setSwipeToRefresh(final SwipeRefreshLayout swipeToRefresh){

        swipeToRefresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeToRefresh.setRefreshing(false);



                    }
                },3000);
            }
        });
    }



    public void getAllOrders(final Activity activity,final RecyclerView recyclerView, final SwipeRefreshLayout swipeToRefresh ){

        swipeToRefresh.setRefreshing(true);

        orderList = new ArrayList<Order>();

        repository.getOrders();

        repository.getOrders(new MyRepository.RepoInterface() {
            @Override
            public void OnOrdersChangeListener(final List<Order> orderList) {

                adapter = new OrdersAdapter(activity,orderList);
                recyclerView.setAdapter(adapter);

                adapter.setOnDeleteClickListener(new OrdersAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position, Order order) {
                        repository.removeOrder(order);
                        //To refresh the recyclerView
                        repository.getOrders();
                    }
                });

                adapter.setOnEditClickListener(new OrdersAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position, Order order) {

                        Intent intent = new Intent(activity,NewOrderActivity.class);
                        intent.putExtra("imageURL",order.getImage_URL());
                        intent.putExtra("color",order.getColor());
                        intent.putExtra("quantity",order.getQuantity());
                        intent.putExtra("OrderID",order.getId());
                        intent.putExtra("Ahmed","1");


                        activity.startActivityForResult(intent,20);

                    }
                });
            }

        });

        repository.getDataStatus(new MyRepository.RepoCancelled() {
            @Override
            public void isCancelled(Boolean isCancelled) {
                Log.e("isCancelled",isCancelled+"");
                if(isCancelled){
                    Toast.makeText(activity, "failed to connect to database", Toast.LENGTH_SHORT).show();
                }
            }
        });


        swipeToRefresh.setRefreshing(false);

    }


    public void fabButtonStartNewOrderActivity(Activity activity){
        Intent intent =  new Intent(activity,NewOrderActivity.class);
        activity.startActivityForResult(intent,20);
    }


}
