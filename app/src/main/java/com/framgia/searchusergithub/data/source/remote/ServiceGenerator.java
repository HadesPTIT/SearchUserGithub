package com.framgia.searchusergithub.data.source.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hades on 8/27/2018.
 */
public class ServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";
    private static Retrofit sRetrofit;

    public static GithubService createService() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient =
                new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return sRetrofit.create(GithubService.class);
    }

}
