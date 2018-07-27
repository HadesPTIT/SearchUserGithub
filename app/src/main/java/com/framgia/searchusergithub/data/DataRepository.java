package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.User;

import java.util.List;

public class DataRepository {

    private UserDataSource mLocalDataSource;
    private UserDataSource mRemoteDataSource;

    private static DataRepository mDataRepos;

    public DataRepository(UserDataSource localDataSource, UserDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized DataRepository getInstance(UserDataSource remoteDataSource,
                                                          UserDataSource localDataSource) {
        if (mDataRepos == null) {
            mDataRepos = new DataRepository(remoteDataSource, localDataSource);
        }
        return mDataRepos;
    }

    public void getUsers(String keyword, int limit, final UserDataSource.GetUserCallback callback) {
        mRemoteDataSource.getUsers(keyword, limit, new UserDataSource.GetUserCallback() {
            @Override
            public void onSuccess(List<User> users) {
                callback.onSuccess(users);
            }

            @Override
            public void onFailed(Throwable throwable) {
                callback.onFailed(throwable);
            }

            @Override
            public void onNetworkError() {
                callback.onNetworkError();
            }
        });
    }
}
