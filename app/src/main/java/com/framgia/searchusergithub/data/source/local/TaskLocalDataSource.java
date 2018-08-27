package com.framgia.searchusergithub.data.source.local;

import com.framgia.searchusergithub.data.TaskDataSource;
import com.framgia.searchusergithub.data.model.Task;
import com.framgia.searchusergithub.utils.AppExecutors;

import java.util.List;

public class TaskLocalDataSource extends TaskDataSource {

    private static volatile TaskLocalDataSource mInstance;

    private TaskDao mTaskDao;

    private AppExecutors mAppExecutors;

    private TaskLocalDataSource(AppExecutors appExecutors, TaskDao taskDao) {
        mTaskDao = taskDao;
        mAppExecutors = appExecutors;
    }

    public static TaskLocalDataSource getInstance(AppExecutors appExecutors, TaskDao taskDao) {
        if (mInstance == null) {
            mInstance = new TaskLocalDataSource(appExecutors, taskDao);
        }
        return mInstance;
    }

    @Override
    public void getTasks(final LoadTaskCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Task> tasks = mTaskDao.getTasks();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tasks.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTaskLoaded(tasks);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getTask(final String taskId, final GetTaskCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Task task = mTaskDao.getTaskById(taskId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (task != null) {
                            callback.onTaskLoaded(task);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveTask(final Task task) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.insertTask(task);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void clearCompletedTask() {
        Runnable clearRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.deleteCompletedTasks();
            }
        };
        mAppExecutors.diskIO().execute(clearRunnable);
    }
}
