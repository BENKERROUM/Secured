package com.example.mbenkerroum.secured;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mbenkerroum.secured.Dialog.NewPasswordDilaogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Password2DetailActivity extends CustomActivity implements NewPasswordDilaogFragment.OnFragmentInteractionListener{

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
    }

    @Override
    public void showMessage(String s) {
        runOnUiThread(() -> Toast.makeText(Password2DetailActivity.this, s, Toast.LENGTH_SHORT).show());
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
        NewPasswordDilaogFragment newFragment = new NewPasswordDilaogFragment();
        newFragment.show(getSupportFragmentManager(), TAG);

    }

    @Override
    public void onSuccess(Password password) {

    }

    @Override
    public void onCancel() {

    }
}

