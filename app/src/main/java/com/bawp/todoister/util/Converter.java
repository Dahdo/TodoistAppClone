package com.bawp.todoister.util;

import androidx.room.TypeConverter;

import com.bawp.todoister.model.Priority;

import java.util.Date;

public class Converter {
    @TypeConverter
    public Date fromTimeStamp(long value){
        return value == 0 ? null : new Date(value);
    }

    @TypeConverter
    public long fromDate(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public String fromPriority(Priority priority){
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public Priority toPriority(String priority){
        return priority == null ? null : Priority.valueOf(priority);
    }
}
