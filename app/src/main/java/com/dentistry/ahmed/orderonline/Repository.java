package com.dentistry.ahmed.orderonline;

import android.support.annotation.NonNull;
import android.util.Log;
import com.dentistry.ahmed.orderonline.FireBase.MyFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Repository {

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


}
