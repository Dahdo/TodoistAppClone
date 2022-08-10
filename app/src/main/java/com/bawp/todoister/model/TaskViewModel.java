package com.bawp.todoister.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.data.Repository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static Repository repository;
    public final LiveData<List<Task>> allTasks;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTasks = repository.getAllTasks();
    }

    public static void insert(Task task){
        repository.insert(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void update(Task task){
        repository.update(task);
    }
    public LiveData<List<Task>> getAllTasks(){
        return repository.getAllTasks();
    }
    public LiveData<Task> getTask(long id){
       return repository.get(id);
    }
}
