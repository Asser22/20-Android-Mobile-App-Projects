package com.asser.learningapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubtopicsViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("Subtopics", 0)
    private val _subtopicsMap = MutableLiveData<Map<String, List<String>>>()
    val subtopicsMap: LiveData<Map<String, List<String>>> = _subtopicsMap

    init {
        loadSubtopics()
    }

    private fun loadSubtopics() {
        val subtopicsJson = sharedPreferences.getString("subtopicsMap", null)
        if (subtopicsJson != null) {
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val subtopics = Gson().fromJson<Map<String, List<String>>>(subtopicsJson, type)
            _subtopicsMap.value = subtopics
        } else {
            _subtopicsMap.value = emptyMap()
        }
    }

    fun updateSubtopicsForSubject(subjectName: String, subtopics: List<String>) {
        val updatedMap = _subtopicsMap.value.orEmpty().toMutableMap()
        updatedMap[subjectName] = subtopics
        _subtopicsMap.value = updatedMap
        saveSubtopics(updatedMap)
    }

    private fun saveSubtopics(subtopicsMap: Map<String, List<String>>) {
        val editor = sharedPreferences.edit()
        val subtopicsJson = Gson().toJson(subtopicsMap)
        editor.putString("subtopicsMap", subtopicsJson)
        editor.apply()
    }
}
