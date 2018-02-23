package com.example.mbenkerroum.secured.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mbenkerroum.secured.Password;
import com.example.mbenkerroum.secured.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mbenkerroum on 16/02/2018.
 */

public class UpdatePasswordDilaogFragment extends DialogFragment {


    public static final String KEY = "key";
    @BindView(R.id.txtLabel)
    TextView txtLabel;
    @BindView(R.id.btnSave)
    View btnSave;
    @BindView(R.id.btnCancel)
    View btnCancel;

    Unbinder unbinder;

    OnFragmentInteractionListener mListener;
    @BindView(R.id.edtInputName)
    EditText edtInputName;
    @BindView(R.id.edtInputPassword)
    EditText edtInputPassword;

    Password password;
    @BindView(R.id.edtInputDesc)
    AutoCompleteTextView edtInputDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = inflater.inflate(R.layout.dialog_edittext, container, false);
        unbinder = ButterKnife.bind(this, view);
        password = (Password) getArguments().getSerializable(KEY);
        if (password != null) {
            setEdtInputName(password.getPasswordName());
            setEdtInputPassword(password.getPasswordString());
            setEdtInputDesc(password.getPasswordDesc());
        }
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

    public interface OnFragmentInteractionListener {

        void onSuccessUpdatePassword(Password s);

        void onCancelUpdatePassword();
    }

    @OnClick(R.id.btnSave)
    public void onSave(View v) {
        password.setPasswordString(edtInputPassword.getText().toString());
        password.setPasswordName(edtInputName.getText().toString());
        password.setPasswordDesc(edtInputDesc.getText().toString());
        mListener.onSuccessUpdatePassword(password);
        dismiss();
    }

    @OnClick(R.id.btnCancel)
    public void onCancel(View v) {
        mListener.onCancelUpdatePassword();
        dismiss();
    }

    public void setEdtInputName(String text) {
        edtInputName.setText(text);
    }

    public void setEdtInputPassword(String text) {
        edtInputPassword.setText(text);
    }

    public void setEdtInputDesc(String text) {
        edtInputDesc.setText(text);
    }
}
