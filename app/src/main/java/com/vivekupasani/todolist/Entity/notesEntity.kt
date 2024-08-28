package com.vivekupasani.todolist.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "notes")
data class notesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val description: String,
    val date: String,
    val timestamp: Timestamp,
    val priority: Int
)
