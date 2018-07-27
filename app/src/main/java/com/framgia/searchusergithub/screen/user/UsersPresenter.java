package com.framgia.searchusergithub.screen.user;

import com.framgia.searchusergithub.data.model.User;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View mView;

    public UsersPresenter(UsersContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void getUsersInfo(User user) {
        mView.gotoUserDetailActivity(user);
    }
}
