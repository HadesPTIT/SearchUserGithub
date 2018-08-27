package com.framgia.searchusergithub.data;

import com.framgia.searchusergithub.data.model.Task;

import java.util.List;

public abstract class TaskDataSource {

    public interface LoadTaskCallback {

        void onTaskLoaded(List<Task> tasks);

        void onDataNotAvailable();

    }

    public interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();

    }

    public abstract void getTasks(LoadTaskCallback callback);

    public abstract void getTask(String taskId, GetTaskCallback callback);

    public abstract void saveTask(Task task);

    public abstract void clearCompletedTask();

}
