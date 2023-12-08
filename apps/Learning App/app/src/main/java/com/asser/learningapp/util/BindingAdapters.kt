package com.asser.learningapp.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asser.learningapp.adapter.SubjectsAdapter

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<String>?) {
    val adapter = recyclerView.adapter as? SubjectsAdapter
    adapter?.updateItems(items ?: listOf())
}
