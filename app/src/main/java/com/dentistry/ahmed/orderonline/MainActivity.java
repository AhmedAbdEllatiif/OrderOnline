package com.dentistry.ahmed.orderonline;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dentistry.ahmed.orderonline.Adapters.DealsAdapters;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Deals;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

private RecyclerView recyclerView;
private DealsAdapters adapter;
private List<Deals> dealsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  //firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child("name").setValue("Ahmed");*/
      // MyFireBase.getReferenceOnAllUsers().child("Ahmed").setValue();

        recyclerView = findViewById(R.id.deals_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        dealsList =  new ArrayList<>();
    MyFireBase.getReferenceOnDeals().addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                Deals deals = snapshot.getValue(Deals.class);
                Log.e("deal",deals.getURL());
                dealsList.add(deals);
            }
            adapter = new DealsAdapters(MainActivity.this,dealsList);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }
}
