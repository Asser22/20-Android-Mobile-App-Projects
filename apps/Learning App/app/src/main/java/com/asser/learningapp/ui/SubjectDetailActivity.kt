package com.asser.learningapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asser.learningapp.R

class SubjectDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_detail)

        // Retrieve the subject name if passed
        val subjectName = intent.getStringExtra("SUBJECT_NAME")

        if (savedInstanceState == null) {
            // Create an instance of the SubjectDetailFragment
            val fragment = SubjectDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("SUBJECT_NAME", subjectName)
                }
            }

            // Load the SubjectDetailFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
