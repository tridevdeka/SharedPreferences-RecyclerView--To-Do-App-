package com.tricoder.taskapp;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> tasksArrayList;
    OnClickListener onClickListener;

    public TaskAdapter(ArrayList<TaskModel> tasksArrayList,OnClickListener onClickListener) {
        this.tasksArrayList = tasksArrayList;
        this.onClickListener=onClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        TaskModel taskModel=tasksArrayList.get(position);
        holder.tvTaskText.setText(taskModel.getText());
        holder.tvDate.setText(DateFormat.format("dd-Jul-yy",taskModel.getCreated()));
        holder.tvDay.setText(DateFormat.format("E",taskModel.getCreated()));
        holder.tvTime.setText(DateFormat.format("hh:mm A",taskModel.getCreated()));
        holder.checkBox.setChecked(taskModel.isChecked());

        holder.itemView.setOnClickListener(v->
                onClickListener.onItemClicked(position));
        holder.checkBox.setOnCheckedChangeListener((v,checked)->
                onClickListener.onItemChecked(position,checked));
    }

    @Override
    public int getItemCount() {
        return tasksArrayList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvDay,tvTime,tvTaskText;
        CheckBox checkBox;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate=itemView.findViewById(R.id.tvDate);
            tvDay=itemView.findViewById(R.id.tvDay);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvTaskText=itemView.findViewById(R.id.tvTaskText);
            checkBox=itemView.findViewById(R.id.boxCheck);
        }
    }
}
