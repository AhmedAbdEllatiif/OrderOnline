package com.dentistry.ahmed.orderonline;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginViewModel extends AndroidViewModel {

    private Repository repository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public void Login(final Activity activity, String txt_email, String txt_password ){

        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            Toast.makeText(activity, "All fields required ", Toast.LENGTH_SHORT).show();
        } else {

            if (repository.checkLoginUser(txt_email,txt_password)){
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
