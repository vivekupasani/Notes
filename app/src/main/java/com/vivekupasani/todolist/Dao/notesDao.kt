package com.vivekupasani.todolist.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vivekupasani.todolist.Entity.notesEntity

@Dao
interface notesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(note: notesEntity)

    @Update
    fun updateData(note: notesEntity)

    // Fetch all notes
    @Query("SELECT * FROM notes")
    fun getData(): LiveData<List<notesEntity>>

    // Delete a note by its ID
    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteData(noteId: Int)
}
