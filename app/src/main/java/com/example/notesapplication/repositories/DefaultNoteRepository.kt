package com.example.notesapplication.repositories

import androidx.lifecycle.LiveData
import com.example.notesapplication.data.local.Note
import com.example.notesapplication.data.local.NotesDao
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(
    private val noteDao: NotesDao
) : NoteRepository {

    override fun allNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun insert(note: Note) {
        noteDao.insert(note)

    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    override suspend fun update(note: Note) {
        noteDao.update(note)
    }
}