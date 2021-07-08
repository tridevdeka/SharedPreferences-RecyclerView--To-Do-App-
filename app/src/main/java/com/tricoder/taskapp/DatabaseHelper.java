package com.tricoder.taskapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DatabaseHelper {

   public static final Gson gson=new Gson();

    public void saveTasks(Context context, ArrayList<TaskModel>tasks){
        SharedPreferences sharedPreferences= context.getSharedPreferences("name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("key", gson.toJson(tasks));
        editor.apply();

    }

    public ArrayList<TaskModel> getTasks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        String tasksString = sharedPreferences.getString("key", null);
        Type taskType = new TypeToken<ArrayList<TaskModel>>() {
        }.getType();
        ArrayList<TaskModel> tasks = gson.fromJson(tasksString, taskType);

        if (tasks != null) return tasks;
        else return new ArrayList<>();
    }
}
