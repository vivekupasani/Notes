package com.vivekupasani.todolist.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vivekupasani.todolist.Database.notesDatabase
import com.vivekupasani.todolist.Entity.notesEntity
import com.vivekupasani.todolist.Repository.notesRepository

class notesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: notesRepository

    init {
        val dao = notesDatabase.getDatabaseInstance(application).dao()
        repository = notesRepository(dao)
    }

    fun insertNote(notesEntity: notesEntity) {
        return repository.addData(notesEntity)
    }

    fun updateNote(notesEntity: notesEntity) {
        return repository.updateData(notesEntity)
    }

    fun deleteNote(id: Int) {
        return repository.deleteData(id)
    }

    fun getNotes(): LiveData<List<notesEntity>> = repository.getData()
}