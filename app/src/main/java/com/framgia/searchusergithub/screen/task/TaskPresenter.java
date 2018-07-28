package com.framgia.searchusergithub.screen.task;

import android.util.Log;

import com.framgia.searchusergithub.data.TaskDataRepository;
import com.framgia.searchusergithub.data.TaskDataSource;
import com.framgia.searchusergithub.data.model.Task;

import java.util.List;

public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mView;
    private TaskDataRepository mTaskRepository;

    public TaskPresenter(TaskContract.View view, TaskDataRepository taskRepository) {
        mView = view;
        mTaskRepository = taskRepository;
    }

    @Override
    public void loadTasks() {
        mTaskRepository.getTasks(new TaskDataSource.LoadTaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                mView.showTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showNoTaskAvailable();
            }
        });
    }

    @Override
    public void addNewTask(Task task) {
        mTaskRepository.saveTask(task);
        loadTasks();
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }
}
