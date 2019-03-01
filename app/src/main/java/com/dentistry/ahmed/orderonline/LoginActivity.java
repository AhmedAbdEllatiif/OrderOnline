package com.dentistry.ahmed.orderonline;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dentistry.ahmed.orderonline.ViewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText email, password;
    private Button btn_login;
    private Toolbar toolbar;
    private TextView txt_forgetPassword;

    private String txt_email;
    String txt_password;

    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        clickLoginButton();





    }

    private void initViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        txt_forgetPassword = findViewById(R.id.txt_forgetPassword);
        btn_login = findViewById(R.id.btn_login);
        toolbar = findViewById(R.id.myToolBar);
    }


    private void clickLoginButton() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_email = email.getText().toString().trim();
                txt_password = password.getText().toString().trim();

                loginViewModel.Login(LoginActivity.this,txt_email,txt_password);

            }
        });


       /* txt_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);

                if (email.getText().toString().trim().equals("")) {
                    intent.putExtra("Email", "");
                }else {
                    intent.putExtra("Email",email.getText().toString().trim());
                }
                startActivity(intent);

            }
        });*/

    }

}
