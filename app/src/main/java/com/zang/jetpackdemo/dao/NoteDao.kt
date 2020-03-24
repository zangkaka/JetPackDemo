package com.zang.jetpackdemo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zang.jetpackdemo.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note_table WHERE title LIKE :title")
    suspend fun findNoteById(title: String): Note

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}