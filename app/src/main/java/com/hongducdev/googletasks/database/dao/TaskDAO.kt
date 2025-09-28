package com.hongducdev.googletasks.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hongducdev.googletasks.database.entity.TaskCollection
import com.hongducdev.googletasks.database.entity.TaskEntity

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskCollection(taskCollection: TaskCollection)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(task: TaskEntity)

    @Query("SELECT * FROM task_collection")
    suspend fun getTaskCollections(): List<TaskCollection>

    @Query("SELECT * FROM task WHERE collection_id = :collectionId")
    suspend fun getTasks(collectionId: Int): List<TaskEntity>

    @Query("UPDATE task SET is_favorite = :isFavorite WHERE id = :taskId")
    suspend fun updateTaskFavorite(taskId: Int, isFavorite: Boolean)

    @Query("UPDATE task SET is_completed = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskCompleted(taskId: Int, isCompleted: Boolean)

    @Query("UPDATE task_collection SET title = :newTitle WHERE id = :collectionId")
    suspend fun updateTaskCollectionTitle(collectionId: Int, newTitle: String)

    @Update
    suspend fun updateTaskCollection(taskCollection: TaskCollection)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTaskCollection(taskCollection: TaskCollection)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}