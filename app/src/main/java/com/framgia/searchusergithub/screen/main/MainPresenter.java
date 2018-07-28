package com.framgia.searchusergithub.screen.main;

import android.content.Context;
import android.text.TextUtils;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.data.UserDataRepository;
import com.framgia.searchusergithub.data.UserDataSource;
import com.framgia.searchusergithub.data.model.User;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private UserDataRepository mUserDataRepository;

    public MainPresenter(MainContract.View view, UserDataRepository userDataRepository) {
        this.mView = view;
        this.mUserDataRepository = userDataRepository;
    }

    @Override
    public void search(String keyword, String limit) {

        if (TextUtils.isEmpty(keyword)) {
            mView.setKeyWordError(((Context) mView).getString(R.string.error_field_required));
        } else if (TextUtils.isEmpty(limit)) {
            mView.setLimitError(((Context) mView).getString(R.string.error_field_required));
        } else if (Integer.parseInt(limit) > 100 || Integer.parseInt(limit) < 0) {
            mView.setLimitError(((Context) mView).getString(R.string.error_limit_result));
        } else {
            mView.showLoginProgress(true);
            mUserDataRepository.getUsers(keyword, Integer.parseInt(limit), new UserDataSource.GetUserCallback() {
                @Override
                public void onSuccess(List<User> users) {
                    mView.showLoginProgress(false);
                    mView.gotoUsersActivity(users);
                }

                @Override
                public void onFailed(Throwable throwable) {
                }

                @Override
                public void onNetworkError() {
                    mView.showNetworkError();
                }
            });
        }
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void openTaskManager() {
        mView.gotoTaskActivity();
    }

}
