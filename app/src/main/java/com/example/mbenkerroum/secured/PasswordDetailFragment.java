package com.example.mbenkerroum.secured;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A fragment representing a single Password detail screen.
 * This fragment is either contained in a {@link PasswordListActivity}
 * in two-pane mode (on tablets) or a {@link PasswordDetailActivity}
 * on handsets.
 */
public class PasswordDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_PASSWORD_ID = "password_id";
    @BindView(R.id.txtPasswordDisplayer)
    TextView txtPasswordDisplayer;
    @BindView(R.id.btnPasswordCopy)
    View btnPasswordCopy;
    @BindView(R.id.btnPasswordUpdate)
    View btnPasswordUpdate;
    Unbinder unbinder;
    @BindView(R.id.btnPasswordDelete)
    View btnPasswordDelete;

    /**
     * The dummy content this fragment is presenting.
     */
    private Password password;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PasswordDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PASSWORD_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            password = (Password) getArguments().getSerializable(ARG_PASSWORD_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(password.getPasswordName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.password_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        // Show the dummy content as text in a TextView.
        if (password != null) {
            txtPasswordDisplayer.setText(password.getPasswordString());

        }


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnPasswordCopy, R.id.btnPasswordUpdate, R.id.btnPasswordDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPasswordCopy:
                ((Callbacks) getActivity()).onCopy(password);
                break;
            case R.id.btnPasswordUpdate:
                ((Callbacks) getActivity()).onEdit(password);
                break;
            case R.id.btnPasswordDelete:
                ((Callbacks) getActivity()).onDelete(password);
                break;
        }
    }


    interface Callbacks {
        void onDelete(Password password);

        void onCopy(Password password);

        void onEdit(Password password);
    }
}
