package com.example.mbenkerroum.secured;


import android.util.Log;

import java.util.List;


/**
 * Created by mbenkerroum on 15/02/2018.
 */

public class PasswordProvider {

    public static int PASSWORD_ID=551650;
    private static String Tag = "PasswordProvider";


    public static void providePassord(Callback callback) {
        new Thread(() -> {
            Password password = App.get().getDB().productDao().findByPassword(PASSWORD_ID);
            try {
                String passwordString = password.getPasswordString();
                callback.onSuccess(passwordString);
            }catch (NullPointerException e){
                Log.e("Class : "+Tag,e.getMessage());
                callback.onError("No password found");
            }
        }).start();
    }

    public static void updatePassword(String s, Callback callback){

        new Thread(() -> {
            Password password = App.get().getDB().productDao().findByPassword(PASSWORD_ID);
            try {
                password.setPasswordString(s);
                callback.onSuccess(s);
                Log.e("Class : "+Tag,"Password updated ! ");
            }catch (NullPointerException e){
                Log.e("Class : "+Tag,"Creating new password entry..");
                App.get().getDB().productDao().insert(new Password(PASSWORD_ID,s));
                Log.e("Class : "+Tag,"Password updated ! ");
            }
        }).start();

    }


    interface Callback<T> {
         void onSuccess(T t);
         void onError(T t);
    }
}