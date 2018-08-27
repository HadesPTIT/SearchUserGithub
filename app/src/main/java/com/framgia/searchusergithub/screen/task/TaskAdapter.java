package com.framgia.searchusergithub.screen.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.data.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> mTasks;
    private LayoutInflater mInflater;
    private OnTaskClickListener mListener;

    public TaskAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        final Task task = mTasks.get(position);
        holder.cbComplete.setChecked(task.isCompleted());
        holder.tvTitle.setText(task.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTaskClicked(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbComplete;
        TextView tvTitle;

        public TaskViewHolder(View itemView) {
            super(itemView);
            cbComplete = itemView.findViewById(R.id.cb_complete);
            tvTitle = itemView.findViewById(R.id.text_title);
        }
    }

    public void setTasks(List<Task> tasks) {
        this.mTasks = tasks;
        notifyDataSetChanged();
    }

    public void setOnTaskClickListener(OnTaskClickListener listener) {
        mListener = listener;
    }

    interface OnTaskClickListener {

        void onTaskClicked(Task task);

    }

}
