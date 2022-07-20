package com.example.notesapplication.repositories

import androidx.lifecycle.LiveData
import com.example.notesapplication.data.local.Note


interface NoteRepository {

    fun allNotes(): LiveData<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    suspend fun update(note: Note)
}