package com.example.mbenkerroum.secured;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mbenkerroum.secured.Auth.AuthentificatorActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("##################################"+getDatabasePath(App.DATABASE_NAME+".db").getAbsolutePath());
        startActivity(new Intent(this,AuthentificatorActivity.class));
    }


    private void showError(String errorText){
        runOnUiThread(() -> Toast.makeText(MainActivity.this, errorText, Toast.LENGTH_SHORT).show());
    }

    private void displayPassord(String passwordString){
        ((TextView)findViewById(R.id.txtPasswordDisplayer)).setText(passwordString);
    }





}
