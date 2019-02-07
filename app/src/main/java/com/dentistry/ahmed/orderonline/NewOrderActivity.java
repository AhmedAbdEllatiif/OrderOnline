package com.dentistry.ahmed.orderonline;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.Adapters.ItemsAdapter;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewOrderActivity extends AppCompatActivity {
    private NumberPicker numberPicker;

    private List<Item> itemList;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);


        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);


        recyclerView = findViewById(R.id.items_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        itemList = new ArrayList<>();
        MyFireBase.getReferenceOnItems().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Item item = snapshot.getValue(Item.class);
                    Log.e("Item",item.getCompany());
                itemList.add(item);
                }
                Log.e("Item",itemList.size()+"");


                adapter = new ItemsAdapter(NewOrderActivity.this,itemList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(NewOrderActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };
}
