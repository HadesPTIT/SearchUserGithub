package com.framgia.searchusergithub.screen.task;

import com.framgia.searchusergithub.BasePresenter;
import com.framgia.searchusergithub.BaseView;
import com.framgia.searchusergithub.data.model.Task;

import java.util.List;

public class TaskContract {

    interface Presenter extends BasePresenter {

        void loadTasks();

        void addNewTask(Task task);

    }

    interface View extends BaseView<Presenter> {

        void showTasks(List<Task> tasks);

        void showNoTaskAvailable();

    }

}
