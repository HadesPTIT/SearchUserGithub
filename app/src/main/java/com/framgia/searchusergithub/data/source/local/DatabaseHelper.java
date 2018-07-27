package com.framgia.searchusergithub.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "User.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserPersistenceContract.UserEntry.TABLE_NAME + " (" +
                    UserPersistenceContract.UserEntry.COLUMN_ENTRY_ID + TEXT_TYPE + "PRIMARY KEY" + COMMA_SEP +
                    UserPersistenceContract.UserEntry.COLUMN_ENTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    UserPersistenceContract.UserEntry.COLUMN_ENTRY_URL + TEXT_TYPE +
                    UserPersistenceContract.UserEntry.COLUMN_ENTRY_AVATAR + TEXT_TYPE +
                    " )";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
