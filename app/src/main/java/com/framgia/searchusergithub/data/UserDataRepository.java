package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.GitResponse;
import com.framgia.searchusergithub.data.model.User;

import java.util.List;

import io.reactivex.Single;

public class UserDataRepository {

    private UserDataSource mLocalDataSource;
    private UserDataSource mRemoteDataSource;

    private static UserDataRepository mDataRepos;

    public UserDataRepository(UserDataSource localDataSource, UserDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized UserDataRepository getInstance(
            UserDataSource remoteDataSource, UserDataSource localDataSource) {
        if (mDataRepos == null) {
            mDataRepos = new UserDataRepository(remoteDataSource, localDataSource);
        }
        return mDataRepos;
    }

    public Single<GitResponse> getUsers(String keyword, int limit) {
        return mRemoteDataSource.getUsers(keyword, limit);
    }
}
