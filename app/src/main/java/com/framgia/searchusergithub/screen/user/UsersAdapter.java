package com.framgia.searchusergithub.screen.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.data.model.User;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> mUsers;
    private LayoutInflater mInflater;
    private OnUserClickListener mListener;

    public UsersAdapter(Context context, List<User> users) {
        mUsers = users;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = mUsers.get(position);
        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onUserClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setOnUserClickListener(OnUserClickListener listener) {
        mListener = listener;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.text_id);
            tvName = itemView.findViewById(R.id.text_name);
        }
    }

    interface OnUserClickListener {
        void onUserClick(User user);
    }
}
