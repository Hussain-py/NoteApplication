package com.example.notesapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapplication.data.local.Note

// annotation for dao class.
@Dao
interface NotesDao {

    // method for adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    // method for deleting our note.
    @Delete
    suspend fun delete(note: Note)

    //method to read all the notes
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    //method  to update the note.
    @Update
    suspend fun update(note: Note)

}