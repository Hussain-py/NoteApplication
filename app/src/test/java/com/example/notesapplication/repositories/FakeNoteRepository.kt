package com.example.notesapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapplication.data.local.Note

class FakeNoteRepository : NoteRepository{

    private val NotesList = mutableListOf<Note>()

    private val getAllNotes = MutableLiveData<List<Note>>(NotesList)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        getAllNotes.postValue(NotesList)
    }

    override fun allNotes(): LiveData<List<Note>> {
        return getAllNotes
    }

    override suspend fun insert(note: Note) {
        NotesList.add(note)
        refreshLiveData()
    }

    override suspend fun delete(note: Note) {
        NotesList.remove(note)
        refreshLiveData()
    }

    override suspend fun update(note: Note) {
        NotesList.add(note)
        refreshLiveData()
    }


}