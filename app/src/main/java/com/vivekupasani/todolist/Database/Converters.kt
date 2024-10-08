package com.vivekupasani.todolist.Database

import androidx.room.TypeConverter
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun fromTimestamp(value : Long) : Timestamp?{
        return value?.let {
            Timestamp(it)
        }
    }

    @TypeConverter
    fun timestamptolong(timestamp: Timestamp) : Long?{
        return timestamp.time
    }
}