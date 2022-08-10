package com.bawp.todoister.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bawp.todoister.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);

    @Query("DELETE FROM task_table")
    void deteleAll();

    @Update
    void update(Task task);

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM task_table WHERE task_table.task == :id")
    LiveData<Task> get(long id);

    @Delete
    void deleteTask(Task task);
}
