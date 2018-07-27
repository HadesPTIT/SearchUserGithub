package com.framgia.searchusergithub.data.source.local;

import android.content.Context;

public class UserLocalDataSource {

    private static UserLocalDataSource mInstance;

    private DatabaseHelper mDatabaseHelper;

    private UserLocalDataSource(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static UserLocalDataSource getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserLocalDataSource(context);
        }
        return mInstance;
    }



}
