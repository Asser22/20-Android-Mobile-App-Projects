package com.asser.learningapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Define the entities that belong to the database and set the version number.
@Database(entities = [Subject::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Abstract function to get the SubjectDao.
    abstract fun subjectDao(): SubjectDao
}
