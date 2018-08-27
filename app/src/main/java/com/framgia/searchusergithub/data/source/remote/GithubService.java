package com.framgia.searchusergithub.data.source.remote;

import com.framgia.searchusergithub.data.model.GitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hades on 8/27/2018.
 */
public interface GithubService {

    @GET("search/users")
    Call<GitResponse> listUsers(@Query("q") String name, @Query("per_page") int limit);
}
