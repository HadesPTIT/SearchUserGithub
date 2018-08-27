package com.framgia.searchusergithub.screen.user;

import com.framgia.searchusergithub.BasePresenter;
import com.framgia.searchusergithub.BaseView;
import com.framgia.searchusergithub.data.model.User;

public class UsersContract {

    interface Presenter extends BasePresenter {
        void getUsersInfo(User user);
    }

    interface View extends BaseView<Presenter> {
        void gotoUserDetailActivity(User user);
    }

}
