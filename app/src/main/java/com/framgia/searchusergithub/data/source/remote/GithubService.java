package com.framgia.searchusergithub.data.source.remote;

import com.framgia.searchusergithub.data.model.GitResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hades on 8/27/2018.
 */
public interface GithubService {

    @GET("search/users")
    Single<GitResponse> listUsers(@Query("q") String name, @Query("per_page") int limit);
}
