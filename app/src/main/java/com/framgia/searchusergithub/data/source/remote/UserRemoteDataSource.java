package com.framgia.searchusergithub.data.source.remote;

import com.framgia.searchusergithub.data.UserDataSource;
import com.framgia.searchusergithub.data.model.GitResponse;

import io.reactivex.Single;

public class UserRemoteDataSource extends UserDataSource {

    private static UserRemoteDataSource mInstance;

    public static synchronized UserRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new UserRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public Single<GitResponse> getUsers(String keyword, int limit) {

        GithubService mService = ServiceGenerator.createService();
        return mService.listUsers(keyword, limit);
    }

}
