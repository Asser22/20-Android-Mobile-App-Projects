package com.asser.learningapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val _notes = MutableLiveData<String>()
    val notes: LiveData<String> = _notes

    private val sharedPreferences = application.getSharedPreferences("Notes", 0)

    fun loadNotes(subtopicName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Retrieve the notes from SharedPreferences
            val loadedNotes = sharedPreferences.getString(subtopicName, "") ?: ""
            _notes.postValue(loadedNotes)
        }
    }

    fun saveNotes(subtopicName: String, notes: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Save the notes in SharedPreferences
            with(sharedPreferences.edit()) {
                putString(subtopicName, notes)
                apply()
            }
        }
    }
}
