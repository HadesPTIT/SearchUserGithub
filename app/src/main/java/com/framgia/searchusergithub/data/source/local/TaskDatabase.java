package com.framgia.searchusergithub.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.framgia.searchusergithub.data.model.Task;

/**
 * The Room Database that contains the Task table.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static final String DB_NAME = "Task.db";
    private static TaskDatabase mInstance;

    public abstract TaskDao taskDao();

    private static final Object sLock = new Object();

    public static TaskDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TaskDatabase.class, DB_NAME)
                        .build();
            }
            return mInstance;
        }
    }
}
