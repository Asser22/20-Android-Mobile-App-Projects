package com.asser.learningapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.asser.learningapp.viewmodel.NotesViewModel
import com.asser.learningapp.R

class NotesFragment : Fragment() {

    private lateinit var notesEditText: EditText
    private val viewModel: NotesViewModel by activityViewModels()
    private var subtopicName: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subtopicName = arguments?.getString("SUBTOPIC_NAME") ?: ""
        notesEditText = view.findViewById(R.id.notesEditText)

        viewModel.notes.observe(viewLifecycleOwner, { notes ->
            notesEditText.setText(notes)
        })

        viewModel.loadNotes(subtopicName)
    }

    override fun onPause() {
        super.onPause()
        val notes = notesEditText.text.toString()
        viewModel.saveNotes(subtopicName, notes)
    }
}
