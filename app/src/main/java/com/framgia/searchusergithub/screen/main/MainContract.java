package com.framgia.searchusergithub.screen.main;

import com.framgia.searchusergithub.BasePresenter;
import com.framgia.searchusergithub.BaseView;
import com.framgia.searchusergithub.data.model.User;

import java.util.List;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void search(String keyword, String limit);

        void openTaskManager();

    }

    interface View extends BaseView<Presenter> {

        void setKeyWordError(int errorCode);

        void setLimitError(int errorCode);

        void showLoginProgress(boolean show);

        void showNetworkError();

        void gotoUsersActivity(List<User> users);

        void gotoTaskActivity();

    }
}
