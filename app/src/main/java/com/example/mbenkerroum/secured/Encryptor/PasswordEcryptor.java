package com.example.mbenkerroum.secured.Encryptor;

import android.util.Log;

import com.example.mbenkerroum.secured.Password;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mbenkerroum on 23/02/2018.
 */

public class PasswordEcryptor {

    private static final String TAG = "Ecryptor";
    private static String passwordApp = "password";

    public static Password encrypt(Password password){
        String encryptedMsg ="ERROR";
        try {
            encryptedMsg = AESCrypt.encrypt(passwordApp, password.getPasswordString());
        }catch (GeneralSecurityException e){
            Log.e(TAG,e.getLocalizedMessage());
        }
        password.setPasswordString(encryptedMsg);
        return password;
    }

    public static Password decrypt(Password password){
        String messageAfterDecrypt="ERROR";
        try {
            messageAfterDecrypt = AESCrypt.decrypt(passwordApp, password.getPasswordString());
        }catch (GeneralSecurityException e){
            Log.e(TAG,e.getLocalizedMessage());
        }
        password.setPasswordString(messageAfterDecrypt);
        return password;
    }

    public static List<Password> decryptAll(List<Password> passwords){
        List<Password> newPasswors = new LinkedList<>();
        for(Password password : passwords){
            newPasswors.add(decrypt(password));
        }
        return passwords;

    }

}
