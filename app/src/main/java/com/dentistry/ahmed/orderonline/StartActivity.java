package com.dentistry.ahmed.orderonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    private Button btn_login,btn_register;

    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn_login = findViewById(R.id.btn_login_start);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,LoginActivity.class));
                finish();
            }
        });

        //check if user is already logged in with firebase or facebook
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null /*|| AccessToken.getCurrentAccessToken() != null*/){
            Log.e("start","not null");
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
        }



    }
}
