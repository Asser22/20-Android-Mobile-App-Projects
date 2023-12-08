package com.asser.learningapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("Subjects", 0)
    private val _subjectsList = MutableLiveData<List<String>>()
    val subjectsList: LiveData<List<String>> = _subjectsList

    init {
        loadSubjects()
    }

    private fun loadSubjects() {
        // Initialize with default values
        val defaultSubjects = listOf("Mathematics", "Computer Science", "Physics", "Japanese")
        _subjectsList.value = defaultSubjects

        // Overwrite with saved subjects if they exist
        val subjectsJson = sharedPreferences.getString("subjectsList", null)
        if (subjectsJson != null) {
            val type = object : TypeToken<List<String>>() {}.type
            val subjects = Gson().fromJson<List<String>>(subjectsJson, type)
            _subjectsList.value = subjects
        }
    }

    fun addSubject(subject: String) {
        val updatedList = _subjectsList.value?.toMutableList() ?: mutableListOf()
        updatedList.add(subject)
        _subjectsList.value = updatedList
        saveSubjects()
    }

    fun editSubject(oldSubject: String, newSubject: String) {
        val updatedList = _subjectsList.value?.toMutableList() ?: mutableListOf()
        val index = updatedList.indexOf(oldSubject)
        if (index != -1) {
            updatedList[index] = newSubject
            _subjectsList.value = updatedList
            saveSubjects()
        }
    }

    fun deleteSubject(subject: String) {
        val updatedList = _subjectsList.value?.toMutableList() ?: mutableListOf()
        updatedList.remove(subject)
        _subjectsList.value = updatedList
        saveSubjects()
    }

    private fun saveSubjects() {
        val editor = sharedPreferences.edit()
        val subjectsJson = Gson().toJson(_subjectsList.value)
        editor.putString("subjectsList", subjectsJson)
        editor.apply()
    }
}
