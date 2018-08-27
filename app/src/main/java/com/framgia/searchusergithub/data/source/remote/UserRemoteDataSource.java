package com.framgia.searchusergithub.data.source.remote;

import com.framgia.searchusergithub.data.UserDataSource;
import com.framgia.searchusergithub.data.model.GitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteDataSource extends UserDataSource {

    private static UserRemoteDataSource mInstance;

    public static synchronized UserRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new UserRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void getUsers(String keyword, int limit, final GetUserCallback callback) {

        GithubService mService = ServiceGenerator.createService();
        mService.listUsers(keyword, limit).enqueue(new Callback<GitResponse>() {
            @Override
            public void onResponse(Call<GitResponse> call, Response<GitResponse> response) {
                if (response.isSuccessful()) {
                    GitResponse listUsers = response.body();
                    callback.onSuccess(listUsers.getUsers());
                } else {
                    callback.onFailed(new Throwable("Error"));
                }
            }

            @Override
            public void onFailure(Call<GitResponse> call, Throwable t) {
                callback.onFailed(new Throwable("Error"));
            }
        });

    }

}
