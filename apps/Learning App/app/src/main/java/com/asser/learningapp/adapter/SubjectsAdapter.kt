package com.asser.learningapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asser.learningapp.R


class SubjectsAdapter(private var subjects: MutableList<String>, private val onItemAction: (String, String) -> Unit) : RecyclerView.Adapter<SubjectsAdapter.ViewHolder>() {

    fun updateItems(newSubjects: List<String>) {
        subjects.clear()
        subjects.addAll(newSubjects)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define your ViewHolder's views here, for example:
        val textViewSubject: TextView = view.findViewById(R.id.textViewSubject)
        val editButton: ImageButton = view.findViewById(R.id.editButton)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        holder.textViewSubject.text = subject
        holder.editButton.setOnClickListener { onItemAction(subject, "edit") }
        holder.deleteButton.setOnClickListener { onItemAction(subject, "delete") }
        holder.itemView.setOnClickListener { onItemAction(subject, "click") }
    }

    override fun getItemCount(): Int = subjects.size

    fun updateSubjects(newSubjects: List<String>) {
        subjects.clear()
        subjects.addAll(newSubjects)
        notifyDataSetChanged()
    }
}

