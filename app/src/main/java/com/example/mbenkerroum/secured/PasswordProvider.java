package com.example.mbenkerroum.secured;


import android.util.Log;

import com.example.mbenkerroum.secured.Encryptor.PasswordEcryptor;

import java.util.List;


/**
 * Created by mbenkerroum on 15/02/2018.
 */

public class PasswordProvider {


    private static String Tag = "PasswordProvider";


    public static void getAllPasswords(Callback <List<Password>,String> callback){

        new Thread(() -> {
            try {
                List<Password> passwords =App.get().getDB().passwordDao().getAll();
                callback.onSuccess(PasswordEcryptor.decryptAll(passwords));
            }catch (Exception e){
                callback.onError(e.getLocalizedMessage());
                Log.e(Tag,e.getLocalizedMessage());
            }
        }).start();
    }

    public static void saveNewPassword(Password password,Callback<String,String> callback){
        new Thread(() -> {
            try {
                App.get().getDB().passwordDao().insert(PasswordEcryptor.encrypt(password));
                callback.onSuccess("Password saved !");
            } catch (Exception e) {
                Log.e(Tag, e.getLocalizedMessage());
                callback.onError("Error");
            }
        }).start();
    }

    public static void removePassword(Password password,Callback<String,String> callback){
        new Thread(() -> {
            try {
                App.get().getDB().passwordDao().delete(password);
                callback.onSuccess("Password removed !");
            } catch (Exception e) {
                Log.e(Tag, e.getLocalizedMessage());
                callback.onError("Error");
            }
        }).start();
    }

    public static void updatePassword(Password password,Callback<String,String> callback){
        new Thread(() -> {
            try {
                App.get().getDB().passwordDao().update(PasswordEcryptor.encrypt(password));
                callback.onSuccess("Password updated !");
            } catch (Exception e) {
                Log.e(Tag, e.getLocalizedMessage());
                callback.onError("Error");
            }
        }).start();
    }


    interface Callback<T,E> {
         void onSuccess(T t);
         void onError(E e);
    }
}