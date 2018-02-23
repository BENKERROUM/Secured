package com.example.mbenkerroum.secured.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mbenkerroum.secured.CustomActivity;
import com.example.mbenkerroum.secured.PasswordListActivity;
import com.example.mbenkerroum.secured.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthentificatorActivity extends CustomActivity implements Authentificator.AuthentificatorCallbacks, AuthentificatorDialog.OnFragmentInteractionListener {

    private static final String TAG = "AuthentificatorActivity";
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentificator);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Authentificator.getInstance().getUserPassword(this);
        edtpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("###########################a" + password);
                System.out.println("###########################b" + charSequence);
                if(edtpassword.getText().toString().equals(password)){
                    startActivity(new Intent(AuthentificatorActivity.this,PasswordListActivity.class));
                    finish();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void showMessage(String s) {
        runOnUiThread(() -> Toast.makeText(AuthentificatorActivity.this, s, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onSuccess(String password) {
        this.password = password;
    }

    @Override
    public void onUnregistred() {
        showPasswordDialog();
    }

    @Override
    public void onPasswordSet() {

    }

    @Override
    public void onConfirm(String s) {
        Authentificator.getInstance().setUserPassword(s, this);

    }

    private void showPasswordDialog() {
        AuthentificatorDialog authentificatorDialog = new AuthentificatorDialog();
        authentificatorDialog.setCancelable(false);
        authentificatorDialog.show(getSupportFragmentManager(), TAG);
    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    public void onViewClicked(View view) {
        String text = ((Button)view).getText().toString();
        edtpassword.setText(edtpassword.getText().toString()+text);
    }

}
