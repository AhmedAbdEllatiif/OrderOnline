package com.dentistry.ahmed.orderonline.ViewModel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.MainActivity;
import com.dentistry.ahmed.orderonline.Repository.MyRepository;

public class LoginViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository();
    }

    public void Login(final Activity activity, String txt_email, String txt_password ){

        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            Toast.makeText(activity, "All fields required ", Toast.LENGTH_SHORT).show();
        } else {

            if (myRepository.checkLoginUser(txt_email,txt_password)){
                Intent intent = new Intent(activity,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                Log.e("login","clicked");
                activity.finish();
            }else {
                Toast.makeText(activity, "Auth failed...!", Toast.LENGTH_SHORT).show();
                Log.e("login","clicked But failed");
            }


        }
    }

}
