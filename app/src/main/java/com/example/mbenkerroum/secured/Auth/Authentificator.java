package com.example.mbenkerroum.secured.Auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mbenkerroum.secured.App;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mbenkerroum on 23/02/2018.
 */

public class Authentificator {

    private static final String User_Key = "UserKey";
    private static final String UserPassword_Key = "UserPasswordKey";
    private static final Authentificator ourInstance = new Authentificator();

    public static Authentificator getInstance() {
        return ourInstance;
    }

    private Authentificator() {
    }

    public void setUserPassword(String password,AuthentificatorCallbacks authentificatorCallbacks){
        SharedPreferences.Editor editor =  App.get().getSharedPreferences(User_Key, MODE_PRIVATE).edit();
        editor.putString(UserPassword_Key, password);
        editor.apply();
        authentificatorCallbacks.onPasswordSet();
    }

    public void getUserPassword(AuthentificatorCallbacks authentificatorCallbacks){
        SharedPreferences prefs = App.get().getSharedPreferences(User_Key, MODE_PRIVATE);
        String restoredText = prefs.getString(UserPassword_Key, null);
        if (restoredText == null) {
            authentificatorCallbacks.onUnregistred();
        }
        else {
            authentificatorCallbacks.onSuccess(restoredText);
        }
    }



    interface AuthentificatorCallbacks{
        void onSuccess(String s);
        void onUnregistred();
        void onPasswordSet();
    }
}
