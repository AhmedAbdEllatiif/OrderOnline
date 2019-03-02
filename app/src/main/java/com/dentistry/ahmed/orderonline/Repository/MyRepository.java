package com.dentistry.ahmed.orderonline.Repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dentistry.ahmed.orderonline.Adapters.DealsAdapters;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Deals;
import com.dentistry.ahmed.orderonline.Model.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyRepository {

 /***************************************************************************************************/
 //Chech if the email and password is right
    private Boolean isUser = false ;
    public boolean checkLoginUser(String txt_email, String txt_password){
        MyFireBase.getAuth().signInWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("isSuccessfulLogin",task.isSuccessful()+"");
                        if (task.isSuccessful()){
                           isUser = true;
                        }
                        else {
                           isUser = false;
                        }

                    }
                });
        return isUser;
    }

    /***************************************************************************************************/

    //RecyclerView Deals OrdersActivity
    private DealsAdapters dealsAdapter;
    public void getAllDeals(final Activity activity, final RecyclerView recyclerView, final List<Deals> dealsList){
        MyFireBase.getReferenceOnDeals().orderByChild("ds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Deals deals = snapshot.getValue(Deals.class);

                    dealsList.add(deals);
                }


                dealsAdapter = new DealsAdapters(activity,dealsList);
                recyclerView.setAdapter(dealsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    /***************************************************************************************************/



    public void removeOrder(Order order){
        MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid())
                .child(order.getId())
                .removeValue();
    }

    /***************************************************************************************************/



    /***************************************************************************************************/
    //To get Orders
    public void getOrders(){
        MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Order> orderList = new ArrayList<>();
                orderList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Order order = snapshot.getValue(Order.class);
                    orderList.add(order);
                }
                Collections.reverse(orderList);

                if(orderList!= null){
                    orders.OnOrdersChangeListener(orderList);
                }
                cancelled.isCancelled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                cancelled.isCancelled(true);
            }
        });
    }
    /***************************************************************************************************/



    private RepoInterface orders;
    private RepoCancelled cancelled;


    public void getOrders(RepoInterface orders) {
        this.orders = orders;
    }

    public void getDataStatus(RepoCancelled cancelled) {
        this.cancelled = cancelled;
    }

    public interface RepoInterface {
         void OnOrdersChangeListener(List<Order> orderList);

}

    public interface RepoCancelled{
        void isCancelled(Boolean isCancelled);
    }




}
