package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.User;

import java.util.List;

public abstract class UserDataSource {

    public interface GetUserCallback {

        void onSuccess(List<User> users);

        void onFailed(Throwable throwable);

        void onNetworkError();

    }

    public abstract void getUsers(String keyword, int limit, GetUserCallback callback);

}
