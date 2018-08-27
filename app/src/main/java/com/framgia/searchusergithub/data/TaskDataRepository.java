package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.Task;

import java.util.List;

public class TaskDataRepository extends TaskDataSource {

    private static TaskDataRepository mInstance = null;

    private final TaskDataSource mTaskLocalDataSource;
    private final TaskDataSource mTaskRemoteDataSource;

    private TaskDataRepository(TaskDataSource taskLocalDataSource, TaskDataSource taskRemoteDataSource) {
        mTaskLocalDataSource = taskLocalDataSource;
        mTaskRemoteDataSource = taskRemoteDataSource;
    }

    public static TaskDataRepository getInstance(TaskDataSource tasksLocalDataSource,
                                                 TaskDataSource tasksRemoteDataSource) {
        if (mInstance == null) {
            mInstance = new TaskDataRepository(tasksLocalDataSource, tasksRemoteDataSource);
        }
        return mInstance;
    }

    @Override
    public void getTasks(final LoadTaskCallback callback) {
        mTaskLocalDataSource.getTasks(new LoadTaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                callback.onTaskLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getTask(String taskId, final GetTaskCallback callback) {
        mTaskLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveTask(Task task) {
        mTaskLocalDataSource.saveTask(task);
    }

    @Override
    public void clearCompletedTask() {
        mTaskLocalDataSource.clearCompletedTask();
    }
}
