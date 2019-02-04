package com.dentistry.ahmed.orderonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  //firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child("name").setValue("Ahmed");*/
      // MyFireBase.getReferenceOnAllUsers().child("Ahmed").setValue();


    }
}
