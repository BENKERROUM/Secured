package com.example.mbenkerroum.secured;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mbenkerroum on 22/02/2018.
 */

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final PasswordListActivity mParentActivity;
    private List<Password> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Password password = (Password) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putSerializable(PasswordDetailFragment.ARG_PASSWORD_ID, password);
                PasswordDetailFragment fragment = new PasswordDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.password_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, PasswordDetailActivity.class);
                intent.putExtra(PasswordDetailFragment.ARG_PASSWORD_ID, password);

                context.startActivity(intent);
            }
        }
    };



    SimpleItemRecyclerViewAdapter(PasswordListActivity parent,
                                  List<Password> items,
                                  boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.password_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getPasswordName());
        holder.mContentView.setText(mValues.get(position).getPasswordString());
        holder.itemView.setTag(mValues.get(position));
        holder.mContainer.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final View mContainer;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
            mContainer = view.findViewById(R.id.container);
        }
    }


}
