package com.example.mbenkerroum.secured;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mbenkerroum on 16/02/2018.
 */

public class SingleInputDilaogFragment extends DialogFragment {


    @BindView(R.id.txtLabel)
    TextView txtLabel;
    @BindView(R.id.edtInput)
    EditText edtInput;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    Unbinder unbinder;

    OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = inflater.inflate(R.layout.dialog_edittext, container, false);
        unbinder=ButterKnife.bind(this, view);
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

    interface OnFragmentInteractionListener {

        void onSuccess(String s);

        void onCancel();
    }

    @OnClick(R.id.btnSave)
    public void onSave(View v ){
        mListener.onSuccess(edtInput.getText().toString());
        dismiss();
    }

    @OnClick(R.id.btnCancel)
    public void onCancel(View v ){
        mListener.onCancel();
        dismiss();
    }
}
