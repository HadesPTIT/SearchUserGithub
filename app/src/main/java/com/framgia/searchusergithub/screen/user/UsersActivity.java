package com.framgia.searchusergithub.screen.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.constant.Constant;
import com.framgia.searchusergithub.data.model.User;
import com.framgia.searchusergithub.screen.detail.UserDetailActivity;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    private RecyclerView mUserRecycler;
    private UsersAdapter mAdapter;
    private ArrayList<User> mUsers;
    private UsersContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mPresenter = new UsersPresenter(this);
        mUsers = getIntent().getParcelableArrayListExtra(Constant.EXTRA_LIST_USERS);

        mUserRecycler = findViewById(R.id.recycler_view_users);
        mAdapter = new UsersAdapter(this, mUsers);
        mUserRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mUserRecycler.setAdapter(mAdapter);

        mAdapter.setOnUserClickListener(new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                mPresenter.getUsersInfo(user);
            }
        });
    }

    @Override
    public void gotoUserDetailActivity(User user) {
        Intent intent = new Intent(UsersActivity.this, UserDetailActivity.class);
        intent.putExtra(Constant.EXTRA_USER_DETAIL, user);
        startActivity(intent);
    }

    @Override
    public void setPresenter(UsersContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
