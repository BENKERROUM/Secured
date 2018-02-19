package com.example.mbenkerroum.secured;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordDetailActivity extends CustomActivity implements SingleInputDilaogFragment.OnFragmentInteractionListener{

    @BindView(R.id.txtPasswordDisplayer)
    TextView txtPasswordDisplayer;
    @BindView(R.id.btnPasswordCopy)
    Button btnPasswordCopy;
    @BindView(R.id.btnPasswordUpdate)
    Button btnPasswordUpdate;

    private static String TAG = "PasswordDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passworddetail);
        ButterKnife.bind(this);
        PasswordProvider.providePassord(new PasswordProvider.Callback<String>() {
            @Override
            public void onSuccess(String o) {
                txtPasswordDisplayer.setText(o);
            }

            @Override
            public void onError(String o) {
                showMessage(o);
            }
        });
    }

    @Override
    public void showMessage(String s) {
        runOnUiThread(() -> Toast.makeText(PasswordDetailActivity.this, s, Toast.LENGTH_SHORT).show());
    }

    public void updatedPasswordText(String password){
        runOnUiThread(() ->  txtPasswordDisplayer.setText(password));
    }

    @OnClick(R.id.btnPasswordCopy)
    public void onCopyClick(View view) {
        Toast.makeText(this, "Copied",
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnPasswordUpdate)
    public void onUpdateClick(View view) {
        SingleInputDilaogFragment newFragment = new SingleInputDilaogFragment();
        newFragment.show(getSupportFragmentManager(), TAG);

    }

    @Override
    public void onSuccess(String password) {
        PasswordProvider.updatePassword(password, new PasswordProvider.Callback<String>() {
            @Override
            public void onSuccess(String o) {
               updatedPasswordText(o);
               showMessage("Updated");
            }

            @Override
            public void onError(String o) {
                showMessage(o);
            }
        });
    }

    @Override
    public void onCancel() {

    }
}

