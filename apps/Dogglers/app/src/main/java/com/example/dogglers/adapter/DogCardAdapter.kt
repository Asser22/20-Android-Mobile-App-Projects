/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

class DogCardAdapter(
    private val context: Context?,
    @LayoutRes private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Step 1: Define a variable for the list of dog data
    private val dogsList: List<Dog> = DataSource.dogs

    /**
     * Step 2: Implement the DogCardViewHolder
     */
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.dog_image)
        val nameTextView: TextView = view.findViewById(R.id.dog_name)
        val ageTextView: TextView = view.findViewById(R.id.dog_age)
        val hobbiesTextView: TextView = view.findViewById(R.id.dog_hobbies)
    }

    /**
     * Step 3: Inflate the layout in onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val layoutId = when (layout) {
            Layout.GRID -> R.layout.grid_list_item
            else -> R.layout.vertical_horizontal_list_item
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return DogCardViewHolder(view)
    }

    /**
     * Step 4: Implement getItemCount to return the length of the list of dogs
     */
    override fun getItemCount(): Int = dogsList.size

    /**
     * Step 5: Bind the data in onBindViewHolder
     */
    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.imageView.setImageResource(dog.imageResourceId)
        holder.nameTextView.text = dog.name
        holder.ageTextView.text = context?.getString(R.string.dog_age, dog.age)
        holder.hobbiesTextView.text = context?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}

