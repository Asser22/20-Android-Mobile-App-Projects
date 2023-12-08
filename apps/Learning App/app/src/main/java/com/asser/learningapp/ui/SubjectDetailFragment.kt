package com.asser.learningapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asser.learningapp.R
import com.asser.learningapp.adapter.SubtopicsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubjectDetailFragment : Fragment() {

    private lateinit var subtopicsAdapter: SubtopicsAdapter
    private lateinit var recyclerView: RecyclerView
    private var subtopicsList = mutableListOf<String>()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var subjectName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subject_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("Subtopics", Context.MODE_PRIVATE)
        subjectName = arguments?.getString("SUBJECT_NAME") ?: ""
        loadSubtopics(subjectName)

        recyclerView = view.findViewById(R.id.recyclerViewSubtopics)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(MainFragment.MarginItemDecoration(16))

        subtopicsAdapter = SubtopicsAdapter(subtopicsList, this::onSubtopicAction)
        recyclerView.adapter = subtopicsAdapter

        val fabAddSubtopic: FloatingActionButton = view.findViewById(R.id.fabAddSubtopic)
        fabAddSubtopic.setOnClickListener {
            showAddSubtopicDialog(subjectName)
        }
    }

    private fun loadSubtopics(subjectName: String) {
        val subtopicsJson = sharedPreferences.getString(subjectName, null)
        if (subtopicsJson != null) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            subtopicsList = Gson().fromJson(subtopicsJson, type)
        } else {
            subtopicsList = getDefaultSubtopicsForSubject(subjectName).toMutableList()
        }
    }

    private fun getDefaultSubtopicsForSubject(subjectName: String): List<String> {
        return when (subjectName) {
            "Mathematics" -> listOf("Pre-Calculus", "Calculus", "Statistics")
            "Computer Science" -> listOf("Discrete Structure", "Mobile Apps", "Database Systems")
            "Physics" -> listOf("Quantum mechanics", "Electromagnetism", "Nuclear physics")
            "Japanese" -> listOf("Hiragana", "Kanji", "Katakana")
            else -> listOf()
        }
    }

    private fun saveSubtopics(subjectName: String) {
        val editor = sharedPreferences.edit()
        val subtopicsJson = Gson().toJson(subtopicsList)
        editor.putString(subjectName, subtopicsJson)
        editor.apply()
    }

    private fun showAddSubtopicDialog(subjectName: String) {
        val editText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Add New Subtopic")
            .setView(editText)
            .setPositiveButton("Add") { dialog, _ ->
                val newSubtopic = editText.text.toString()
                if (newSubtopic.isNotEmpty()) {
                    subtopicsList.add(newSubtopic)
                    subtopicsAdapter.notifyItemInserted(subtopicsList.size - 1)
                    saveSubtopics(subjectName)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun onSubtopicAction(subtopic: String, action: String) {
        when (action) {
            "click" -> navigateToNotes(subtopic)
            "edit" -> showEditSubtopicDialog(subtopic, subjectName)
            "delete" -> confirmDeleteSubtopic(subtopic, subjectName)
        }
    }

    private fun navigateToNotes(subtopic: String) {
        val intent = Intent(requireContext(), NotesActivity::class.java)
        intent.putExtra("SUBTOPIC_NAME", subtopic)
        startActivity(intent)
    }

    private fun showEditSubtopicDialog(subtopic: String, subjectName: String) {
        val editText = EditText(requireContext()).apply { setText(subtopic) }
        AlertDialog.Builder(requireContext())
            .setTitle("Edit Subtopic")
            .setView(editText)
            .setPositiveButton("Save") { dialog, _ ->
                val newSubtopic = editText.text.toString()
                if (!newSubtopic.isBlank()) {
                    val index = subtopicsList.indexOf(subtopic)
                    if (index != -1) {
                        subtopicsList[index] = newSubtopic
                        subtopicsAdapter.notifyItemChanged(index)
                        saveSubtopics(subjectName)
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmDeleteSubtopic(subtopic: String, subjectName: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Subtopic")
            .setMessage("Are you sure you want to delete $subtopic?")
            .setPositiveButton("Delete") { dialog, _ ->
                val index = subtopicsList.indexOf(subtopic)
                if (index != -1) {
                    subtopicsList.removeAt(index)
                    subtopicsAdapter.notifyItemRemoved(index)
                    saveSubtopics(subjectName)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
