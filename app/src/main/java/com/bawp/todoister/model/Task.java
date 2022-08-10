package com.bawp.todoister.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    private long taskId;

    private String task;

    private Priority priority;

    @ColumnInfo(name = "due_date")
    private Date dueDate;

    @ColumnInfo(name = "created_at")
    private Date createAt;

    @ColumnInfo(name = "is_done")
    private boolean isDone;



    public Task(String task, Priority priority, Date dueDate, Date createAt, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createAt = createAt;
        this.isDone = isDone;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", createAt=" + createAt +
                ", isDone=" + isDone +
                '}';
    }
}
