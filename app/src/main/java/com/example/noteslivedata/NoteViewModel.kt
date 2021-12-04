package com.example.noteslivedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var allNotes : LiveData<List<Note>>

    private val repository : NoteRepository
    init {
        val dao = NoteDatabase.getDataBase(application).getNodeDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return allNotes
    }

    fun delete(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
//        allNotes = repository.allNotes
    }

    fun insert(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
//        allNotes = repository.allNotes
    }
}