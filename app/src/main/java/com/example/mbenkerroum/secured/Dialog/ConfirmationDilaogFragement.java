package com.example.mbenkerroum.secured.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.mbenkerroum.secured.R;

/**
 * Created by mbenkerroum on 19/02/2018.
 */

public class ConfirmationDilaogFragement extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are u sure you want to delete this item")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((OnFragmentInteractionListener)getActivity()).onSuccess();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((OnFragmentInteractionListener)getActivity()).onCancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    interface OnFragmentInteractionListener {

        void onSuccess();

        void onCancel();
    }


}