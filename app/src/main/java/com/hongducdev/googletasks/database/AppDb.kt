package com.hongducdev.googletasks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hongducdev.googletasks.database.dao.TaskDAO
import com.hongducdev.googletasks.database.entity.TaskCollection
import com.hongducdev.googletasks.database.entity.TaskEntity

private const val DATABASE_NAME = "tasks_db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [TaskCollection::class, TaskEntity::class],
    version = DATABASE_VERSION,
)

abstract class AppDb : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {
        private var instance: AppDb? = null

        operator fun invoke(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context): AppDb = Room.databaseBuilder(
            context,
            AppDb::class.java,
            DATABASE_NAME
        ).build()
    }
}