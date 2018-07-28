package com.framgia.searchusergithub.screen.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.data.TaskDataRepository;
import com.framgia.searchusergithub.data.model.Task;
import com.framgia.searchusergithub.data.source.local.TaskDatabase;
import com.framgia.searchusergithub.data.source.local.TaskLocalDataSource;
import com.framgia.searchusergithub.utils.AppExecutors;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskActivity extends AppCompatActivity implements TaskContract.View {

    private RecyclerView mRecyclerTask;
    private FloatingActionButton mFabButton;
    private ImageView mImageEmpty;
    private TaskAdapter mAdapter;
    private TaskContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        mImageEmpty = findViewById(R.id.image_empty);
        mRecyclerTask = findViewById(R.id.recycler_view_task);
        mFabButton = findViewById(R.id.fab_add);

        mAdapter = new TaskAdapter(this);
        mRecyclerTask.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerTask.setItemAnimator(new DefaultItemAnimator());
        mRecyclerTask.setAdapter(mAdapter);

        TaskDataRepository taskDataRepository = TaskDataRepository.getInstance(
                TaskLocalDataSource.getInstance(new AppExecutors(), TaskDatabase.getInstance(this).taskDao()),
                null);
        mPresenter = new TaskPresenter(this, taskDataRepository);
        mPresenter.loadTasks();

        mAdapter.setOnTaskClickListener(new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onTaskClicked(Task task) {
                Toast.makeText(TaskActivity.this, task.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                mPresenter.addNewTask(new Task(id, "Hello" + id, "Nothing", new Random().nextBoolean()));
            }
        });

    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void showTasks(List<Task> tasks) {
        mImageEmpty.setVisibility(View.GONE);
        mAdapter.setTasks(tasks);
    }

    @Override
    public void showNoTaskAvailable() {
        mImageEmpty.setVisibility(View.VISIBLE);
    }

}
