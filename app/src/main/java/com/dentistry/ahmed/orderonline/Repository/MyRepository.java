package com.dentistry.ahmed.orderonline.Repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dentistry.ahmed.orderonline.Adapters.DealsAdapters;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.MainActivity;
import com.dentistry.ahmed.orderonline.Model.Deals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
    private DealsAdapters adapter;
    public void getAllDeals(final Activity activity, final RecyclerView recyclerView, final List<Deals> dealsList){
        MyFireBase.getReferenceOnDeals().orderByChild("ds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Deals deals = snapshot.getValue(Deals.class);

                    dealsList.add(deals);
                }


                adapter = new DealsAdapters(activity,dealsList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    /***************************************************************************************************/










}
