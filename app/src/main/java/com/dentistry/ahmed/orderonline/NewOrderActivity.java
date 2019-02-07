package com.dentistry.ahmed.orderonline;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.Adapters.ColorAdapter;
import com.dentistry.ahmed.orderonline.Adapters.ItemsAdapter;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.dentistry.ahmed.orderonline.Model.Color;
import com.dentistry.ahmed.orderonline.Model.Item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewOrderActivity extends AppCompatActivity {
    private NumberPicker numberPicker;

    private List<Item> itemList;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    private List<Color> colorList;
    private RecyclerView recyclerView_color;
    private ColorAdapter adapter_color;

    private Button btn_submit;
    private Button btn_addItem;
    private Button btn_addColor;

    private String color;

Item item = new Item();


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);


        intiViews();

        itemList = new ArrayList<>();
        colorList = new ArrayList<>();


        MyFireBase.getReferenceOnItems().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Item item = snapshot.getValue(Item.class);
                    Log.e("Item",item.getCompany());
                itemList.add(item);
                }
                Log.e("Item",itemList.size()+"");


               /* adapter = new ItemsAdapter(NewOrderActivity.this,itemList);
                recyclerView.setAdapter(adapter);*/

               setAdapter(adapter,itemList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        MyFireBase.getReferenceOnColors().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Color colorItem = snapshot.getValue(Color.class);
                    colorList.add(colorItem);
                }

                setAdapterColor(adapter_color,colorList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String orderID;
                String userName;
                String Email;
                String orderTitle;
                String orderImage_URL;
                String orderPrice;
                String orderDescription;
                String orderColor;
                int quantity;

                orderID = MyFireBase.getCurrentUser().getUid();
                userName = MyFireBase.getCurrentUser().getDisplayName();
                Email  = MyFireBase.getCurrentUser().getEmail();
                orderTitle = item.getName();
                orderImage_URL = item.getName();
                orderPrice = item.getPrice();
                orderDescription = item.getDescription();
                orderColor = getColor();
                numberPicker.setOnValueChangedListener(onValueChangeListener);
                quantity = numberPicker.getValue();

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("id",orderID);
                hashMap.put("userName",userName);
                hashMap.put("Email",Email);
                hashMap.put("title",orderTitle);
                hashMap.put("image_URL",orderImage_URL);
                hashMap.put("price",orderPrice);
                hashMap.put("description",orderDescription);
                hashMap.put("color",orderColor);
                hashMap.put("quantity",quantity);



                MyFireBase.getReferenceOnOrders().child(MyFireBase.getCurrentUser().getUid())
                        .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NewOrderActivity.this, "Order saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NewOrderActivity.this, "Order didn't save", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });



    }

    private void intiViews() {
        recyclerView = findViewById(R.id.items_recyclerView);
        recyclerView_color = findViewById(R.id.color_recyclerView);
        numberPicker = findViewById(R.id.numberPicker);
        btn_addItem = findViewById(R.id.btn_addItem);
        btn_addColor = findViewById(R.id.btn_addColor);
        btn_submit = findViewById(R.id.btn_submit);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);


    }

    SnapHelper snapHelper;
    LinearLayoutManager layoutManager;
    private void setAdapter(ItemsAdapter adapter, final List<Item> itemList){
         layoutManager =
                new LinearLayoutManager(NewOrderActivity.this,LinearLayoutManager.HORIZONTAL,false);
        adapter = new ItemsAdapter(NewOrderActivity.this,itemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);




        btn_addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper.findSnapView(layoutManager);
                int position = recyclerView.getChildAdapterPosition(view);
                item.setImage( itemList.get(position).getImage());
                item.setDescription( itemList.get(position).getDescription());
                item.setPrice(itemList.get(position).getPrice());
            }
        });

    }


    LinearLayoutManager layoutManager_color;
    SnapHelper snapHelper_color;
    private void setAdapterColor(ColorAdapter adapter, final List<Color> colorList){
        layoutManager_color =
                new LinearLayoutManager(NewOrderActivity.this,LinearLayoutManager.HORIZONTAL,false);
        adapter = new ColorAdapter(NewOrderActivity.this,colorList);
        recyclerView_color.setLayoutManager(layoutManager_color);
        recyclerView_color.setAdapter(adapter);
        snapHelper_color = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView_color);



        btn_addColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper_color.findSnapView(layoutManager_color);
                int position = recyclerView_color.getChildAdapterPosition(view);
                setColor(colorList.get(position).getURl());
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
