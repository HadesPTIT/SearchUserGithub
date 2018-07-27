package com.framgia.searchusergithub.screen.detail;

import com.framgia.searchusergithub.data.model.User;

public class UserDetailPresenter implements UserDetailContract.Presenter {

    private UserDetailContract.View mView;

    public UserDetailPresenter(UserDetailContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void getUserInfo(User user) {
        mView.update(user);
    }
}
