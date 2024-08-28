package com.vivekupasani.todolist.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vivekupasani.todolist.Dao.notesDao
import com.vivekupasani.todolist.Entity.notesEntity
import java.util.Objects

@Database(entities = [notesEntity::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class notesDatabase : RoomDatabase() {

    abstract fun dao(): notesDao

    companion object {
        @Volatile
        private var instance: notesDatabase? = null

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE notes ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0")
            }

        }

        fun getDatabaseInstance(context: Context): notesDatabase {
            val temp = instance
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val database =
                    Room.databaseBuilder(context, notesDatabase::class.java, "notes")
                        .allowMainThreadQueries()
                        .addMigrations(migration_1_2)
                        .build()
                instance = database
                return database
            }
        }
    }
}