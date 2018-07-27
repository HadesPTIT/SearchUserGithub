package com.framgia.searchusergithub.screen.main;

import android.content.Intent;
import android.support.constraint.Group;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.constant.Constant;
import com.framgia.searchusergithub.data.DataRepository;
import com.framgia.searchusergithub.data.model.User;
import com.framgia.searchusergithub.data.source.remote.UserRemoteDataSource;
import com.framgia.searchusergithub.screen.BaseActivity;
import com.framgia.searchusergithub.screen.user.UsersActivity;
import com.framgia.searchusergithub.utils.NetworkReceiver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View, NetworkReceiver.NetworkStateListener {

    private EditText mEditKeyWord;
    private EditText mEditLimitResult;
    private Button mBtnSearch;
    private Group mGroup;
    private ProgressBar mProgressBar;
    private boolean isConnected = false;

    private MainContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();

        DataRepository dataRepository = DataRepository.getInstance(null, UserRemoteDataSource.getInstance());
        mPresenter = new MainPresenter(this, dataRepository);
        mPresenter.start();

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    mPresenter.search(mEditKeyWord.getText().toString(), mEditLimitResult.getText().toString());
                } else {
                    showNetworkError();
                }
            }
        });
        setupNetworkBroadcastReceiver(this);
    }

    @Override
    public void setKeyWordError(String error) {
        mEditKeyWord.setError(error);
    }

    @Override
    public void setLimitError(String error) {
        mEditLimitResult.setError(error);
    }

    @Override
    public void showLoginProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        mGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(findViewById(R.id.constraint_main), R.string.msg_no_network_available, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void gotoUsersActivity(List<User> users) {
        ArrayList<User> arrayList = (ArrayList<User>) users;
        Intent intent = new Intent(this, UsersActivity.class);
        intent.putParcelableArrayListExtra(Constant.EXTRA_LIST_USERS, arrayList);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    private void setupView() {
        mBtnSearch = findViewById(R.id.button_search);
        mEditKeyWord = findViewById(R.id.edit_keyword);
        mEditLimitResult = findViewById(R.id.edit_limit);
        mGroup = findViewById(R.id.group);
        mProgressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onNetworkConnected() {
        isConnected = true;
    }

    @Override
    public void onNetworkDisconnected() {
        isConnected = false;
        showNetworkError();
    }
}
