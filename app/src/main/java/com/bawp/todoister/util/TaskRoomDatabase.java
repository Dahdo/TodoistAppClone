package com.bawp.todoister.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.data.TaskDao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TaskRoomDatabase extends RoomDatabase {
    public static final int THREADS_NUMBER = 4;
    public static final String DATABASE_NAME = "todoister_database";
    private static volatile TaskRoomDatabase INSTANCE;
    public static final ExecutorService datadaserWriterExecutor
            = Executors.newFixedThreadPool(THREADS_NUMBER);


   public static final RoomDatabase.Callback sRoomDatabaseCallBack =
           new RoomDatabase.Callback(){
               @Override
               public void onCreate(@NonNull SupportSQLiteDatabase db) {
                   super.onCreate(db);
                   datadaserWriterExecutor.execute(() ->{
                      TaskDao taskDao = INSTANCE.taskDao();
                      taskDao.deteleAll();
                   });
               }
           };

   public static TaskRoomDatabase getDatabase(final Context context){
       if(INSTANCE == null){
           synchronized (TaskRoomDatabase.class){
               if(INSTANCE == null){
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           TaskRoomDatabase.class, DATABASE_NAME).addCallback(sRoomDatabaseCallBack)
                           .build();
               }
           }
       }

       return INSTANCE;
   }

    public abstract TaskDao taskDao();
}
