package com.bawp.todoister;

import android.os.Bundle;

import com.bawp.todoister.adapter.RecyclerViewAdapter;
import com.bawp.todoister.data.TaskDao;
import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "taskTest";
    LiveData<List<Task>> allTasks;
    TaskViewModel taskViewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication())
                .create(TaskViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Task task = new Task("Go to bed" + ++counter, Priority.HIGH, Calendar.getInstance().getTime(),
                    Calendar.getInstance().getTime(), true);
            taskViewModel.insert(task);
        });

        taskViewModel.getAllTasks().observe(this, tasks -> {
           recyclerViewAdapter = new RecyclerViewAdapter(tasks);
           recyclerView.setAdapter(recyclerViewAdapter);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}