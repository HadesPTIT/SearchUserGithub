package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.GitResponse;
import com.framgia.searchusergithub.data.model.User;

import java.util.List;

import io.reactivex.Single;

public abstract class UserDataSource {

    public interface GetUserCallback {

        void onSuccess(List<User> users);

        void onFailed(Throwable throwable);

        void onNetworkError();

    }

    public abstract Single<GitResponse> getUsers(String keyword, int limit);

}
