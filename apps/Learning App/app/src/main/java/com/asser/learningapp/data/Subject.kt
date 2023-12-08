package com.asser.learningapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
