package com.example.mbenkerroum.secured.Auth;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mbenkerroum.secured.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mbenkerroum on 23/02/2018.
 */

public class AuthentificatorDialog extends DialogFragment {

    OnFragmentInteractionListener mListener;
    Unbinder unbinder;
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.btnValid)
    ImageButton btnValid;
    private String text = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = inflater.inflate(R.layout.dialog_authetificator, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    public void onViewClicked(View view) {

        String number = ((Button) view).getText().toString();
        edtpassword.setText(edtpassword.getText().toString() + number);

    }

    @OnClick(R.id.btnValid)
    public void onViewClicked() {
        if(!(edtpassword.getText().toString()).isEmpty()){
        mListener.onConfirm((edtpassword.getText().toString()));
        dismiss();}
    }

    public interface OnFragmentInteractionListener {

        void onConfirm(String s);
    }


}