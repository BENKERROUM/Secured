package com.example.mbenkerroum.secured;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mbenkerroum.secured.Dialog.NewPasswordDilaogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * An activity representing a list of Passwords. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PasswordDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PasswordListActivity extends CustomActivity implements NewPasswordDilaogFragment.OnFragmentInteractionListener{

    private static final String TAG ="PasswordListActivity" ;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.password_list)
    RecyclerView recyclerView;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        FrameLayout passwordDetailContainer = findViewById(R.id.password_detail_container);
        if (passwordDetailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    @Override
    public void showMessage(String s) {
        runOnUiThread(() -> Toast.makeText(PasswordListActivity.this, s, Toast.LENGTH_SHORT).show());
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        PasswordProvider.getAllPasswords(new PasswordProvider.Callback<List<Password>, String>() {
            @Override
            public void onSuccess(List<Password> passwords) {
                runOnUiThread(() -> recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(PasswordListActivity.this, passwords, mTwoPane)));
            }

            @Override
            public void onError(String s) {
                showMessage(s);
            }
        });

    }



    @OnClick(R.id.fab)
    public void newPassword(View view){
        NewPasswordDilaogFragment newFragment = new NewPasswordDilaogFragment();
        newFragment.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void onSuccess(Password s) {
            PasswordProvider.saveNewPassword(s, new PasswordProvider.Callback<String, String>() {
                @Override
                public void onSuccess(String s) {
                    showMessage(s);
                    PasswordProvider.getAllPasswords(new PasswordProvider.Callback<List<Password>, String>() {
                        @Override
                        public void onSuccess(List<Password> passwords) {
                            runOnUiThread(() ->recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(PasswordListActivity.this,passwords,mTwoPane)));
                        }

                        @Override
                        public void onError(String s) {
                            showMessage(s);
                        }
                    });
                }

                @Override
                public void onError(String s) {
                    showMessage(s);
                }
            });
    }

    @Override
    public void onCancel() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        PasswordProvider.getAllPasswords(new PasswordProvider.Callback<List<Password>, String>() {
            @Override
            public void onSuccess(List<Password> passwords) {
                runOnUiThread(() ->recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(PasswordListActivity.this,passwords,mTwoPane)));
            }

            @Override
            public void onError(String s) {
                showMessage(s);
            }
        });

    }
}
