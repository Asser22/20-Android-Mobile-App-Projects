package com.asser.learningapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.asser.learningapp.data.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM Subject")
    fun getAllSubjects(): List<Subject>

    @Insert
    fun insertSubject(subject: Subject)

    @Update
    fun updateSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)

    @Query("DELETE FROM Subject WHERE id = :subjectId")
    fun deleteSubjectById(subjectId: Int)
}
