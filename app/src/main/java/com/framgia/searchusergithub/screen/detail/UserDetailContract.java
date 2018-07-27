package com.framgia.searchusergithub.screen.detail;

import com.framgia.searchusergithub.BasePresenter;
import com.framgia.searchusergithub.BaseView;
import com.framgia.searchusergithub.data.model.User;

public interface UserDetailContract {

    interface Presenter extends BasePresenter {
        void getUserInfo(User user);
    }

    interface View extends BaseView<Presenter> {
        void update(User user);
    }
}
