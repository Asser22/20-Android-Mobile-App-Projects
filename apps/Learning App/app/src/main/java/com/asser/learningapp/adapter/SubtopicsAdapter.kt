package com.asser.learningapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asser.learningapp.R
import com.asser.learningapp.databinding.ItemSubtopicBinding

class SubtopicsAdapter(
    private val subtopics: MutableList<String>,
    private val onItemAction: (String, String) -> Unit
) : RecyclerView.Adapter<SubtopicsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSubtopicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSubtopicBinding>(
            inflater, R.layout.item_subtopic, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subtopic = subtopics[position]
        holder.binding.subtopic = subtopic // Bind the subtopic variable

        holder.binding.editButtonSubtopic.setOnClickListener { onItemAction(subtopic, "edit") }
        holder.binding.deleteButtonSubtopic.setOnClickListener { onItemAction(subtopic, "delete") }
        holder.itemView.setOnClickListener { onItemAction(subtopic, "click") }
    }

    override fun getItemCount() = subtopics.size

    fun updateSubtopics(newSubtopics: List<String>) {
        subtopics.clear()
        subtopics.addAll(newSubtopics)
        notifyDataSetChanged()
    }
}
