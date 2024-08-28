package com.vivekupasani.todolist.Repository

import androidx.lifecycle.LiveData
import com.vivekupasani.todolist.Dao.notesDao
import com.vivekupasani.todolist.Entity.notesEntity

class notesRepository(val dao: notesDao) {

    fun getData(): LiveData<List<notesEntity>> = dao.getData()

    fun addData(notesEntity: notesEntity) {
        return dao.insertData(notesEntity)
    }

    fun updateData(notesEntity: notesEntity) {
        return dao.updateData(notesEntity)
    }

    fun deleteData(id: Int) {
        return dao.deleteData(id)
    }
}

