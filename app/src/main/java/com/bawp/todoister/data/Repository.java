package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class Repository {
    private LiveData<List<Task>> allTasks;
    private TaskDao taskDao;

    public Repository(Application application){
        TaskRoomDatabase taskDatabase = TaskRoomDatabase.getDatabase(application);
        taskDao = taskDatabase.taskDao();

        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }
    public void insert(Task task){
        TaskRoomDatabase.datadaserWriterExecutor.execute(() -> taskDao.insertTask(task));
    }

    public void deleteAll(){
        TaskRoomDatabase.datadaserWriterExecutor.execute(() -> taskDao.deteleAll());
    }

    public void delete(Task task){
        TaskRoomDatabase.datadaserWriterExecutor.execute(() -> taskDao.deleteTask(task));
    }

    public void update(Task task){
        TaskRoomDatabase.datadaserWriterExecutor.execute(() -> taskDao.update(task));
    }

    public LiveData<Task> get(long id){
        return taskDao.get(id);
    }


}
