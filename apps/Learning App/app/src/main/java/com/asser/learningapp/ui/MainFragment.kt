package com.asser.learningapp.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asser.learningapp.viewmodel.MainViewModel
import com.asser.learningapp.R
import com.asser.learningapp.adapter.SubjectsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var subjectsAdapter: SubjectsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        setupAddSubjectButton(view)
        observeSubjects()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(MarginItemDecoration(16))

        subjectsAdapter = SubjectsAdapter(mutableListOf()) { subject, action ->
            when (action) {
                "click" -> navigateToDetail(subject)
                "edit" -> showEditSubjectDialog(subject)
                "delete" -> deleteSubject(subject)
            }
        }
        recyclerView.adapter = subjectsAdapter
    }

    private fun setupAddSubjectButton(view: View) {
        val fabAddSubject: FloatingActionButton = view.findViewById(R.id.fabAddSubject)
        fabAddSubject.setOnClickListener {
            showAddSubjectDialog()
        }
    }

    private fun observeSubjects() {
        viewModel.subjectsList.observe(viewLifecycleOwner, { subjects ->
            subjectsAdapter.updateSubjects(subjects)
        })
    }

    private fun showAddSubjectDialog() {
        val editText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Add New Subject")
            .setView(editText)
            .setPositiveButton("Add") { dialog, _ ->
                val newSubject = editText.text.toString()
                if (newSubject.isNotEmpty()) {
                    viewModel.addSubject(newSubject)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditSubjectDialog(oldSubject: String) {
        val editText = EditText(requireContext()).apply { setText(oldSubject) }
        AlertDialog.Builder(requireContext())
            .setTitle("Edit Subject")
            .setView(editText)
            .setPositiveButton("Save") { dialog, _ ->
                val newSubject = editText.text.toString()
                if (newSubject.isNotEmpty()) {
                    viewModel.editSubject(oldSubject, newSubject)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteSubject(subject: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Subject")
            .setMessage("Are you sure you want to delete $subject?")
            .setPositiveButton("Delete") { dialog, _ ->
                viewModel.deleteSubject(subject)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun navigateToDetail(subject: String) {
        val intent = Intent(requireContext(), SubjectDetailActivity::class.java)
        intent.putExtra("SUBJECT_NAME", subject)
        startActivity(intent)
    }

    class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) != 0) {
                    top = spaceHeight
                }
            }
        }
    }
}
