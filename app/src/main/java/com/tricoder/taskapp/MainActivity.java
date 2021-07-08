package com.tricoder.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private ArrayList<TaskModel> tasksArrayList=new ArrayList<>();
    private  TaskAdapter taskAdapter;
    private EditText edtTaskText;
    private DatabaseHelper databaseHelper=new DatabaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper();
        tasksArrayList=databaseHelper.getTasks(this);

        RecyclerView recyclerView = findViewById(R.id.taskRecyclerView);
        Button btnAddTask = findViewById(R.id.btnAddTask);
        edtTaskText = findViewById(R.id.edtTaskText);

        btnAddTask.setOnClickListener(v->addTask());

        taskAdapter=new TaskAdapter(tasksArrayList,this);
        recyclerView.setAdapter(taskAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    private void addTask() {
        TaskModel taskModels=new TaskModel(edtTaskText.getText().toString());
        tasksArrayList.add(0,taskModels);
        edtTaskText.setText(null);
        taskAdapter.notifyDataSetChanged();

       databaseHelper.saveTasks(getApplicationContext(),tasksArrayList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.deleteTask){
            Iterator<TaskModel> iterator=tasksArrayList.iterator();
            while (iterator.hasNext()){
                if (iterator.next().isChecked()) {
                    iterator.remove();
                }
            }
            taskAdapter.notifyDataSetChanged();
            databaseHelper.saveTasks(this,tasksArrayList);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position) {

        Toast.makeText(this, "position: "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemChecked(int position, boolean checked) {
        tasksArrayList.get(position).setChecked(checked);
        databaseHelper.saveTasks(this,tasksArrayList);
    }


}