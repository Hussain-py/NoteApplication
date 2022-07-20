package com.example.notesapplication.ui

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.notesapplication.data.local.Note
import com.example.notesapplication.other.Constants
import com.example.notesapplication.other.Event
import com.example.notesapplication.other.Resource
import com.example.notesapplication.repositories.NoteRepository
import kotlinx.coroutines.launch


class NoteViewModel  @ViewModelInject constructor(
                                                  private val repository: NoteRepository) : ViewModel()  {




    val allNotes = repository.allNotes()


    private val _insertNoteItemStatus = MutableLiveData<Event<Resource<Note>>>()
    val insertNoteItemStatus: LiveData<Event<Resource<Note>>> = _insertNoteItemStatus



    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

        fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun insertNoteItem(title: String, description: String, timestamp: String,id:Int) {
        if(title.isEmpty() || description.isEmpty() || timestamp.isEmpty() ) {
            _insertNoteItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(title.length > Constants.MAX_NAME_LENGTH) {
            _insertNoteItemStatus.postValue(Event(Resource.error("The name of the title" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        if(description.length > Constants.MAX_DESC_LENGTH) {
            _insertNoteItemStatus.postValue(Event(Resource.error("The description of the Note" +
                    "must not exceed ${Constants.MAX_DESC_LENGTH} characters", null)))
            return
        }

        if(timestamp.length > Constants.MAX_DATE_LENGTH) {
            _insertNoteItemStatus.postValue(Event(Resource.error("The Date of the Note" +
                    "must not exceed ${Constants.MAX_DATE_LENGTH} characters", null)))
            return
        }

        val NoteItem = Note(title,description,timestamp,id)
        addNote(NoteItem)
        _insertNoteItemStatus.postValue(Event(Resource.success(NoteItem)))
    }


}