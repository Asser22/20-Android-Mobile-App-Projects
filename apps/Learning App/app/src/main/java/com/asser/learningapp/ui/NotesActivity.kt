package com.asser.learningapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asser.learningapp.R

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        if (savedInstanceState == null) {
            val subtopicName = intent.getStringExtra("SUBTOPIC_NAME") ?: ""
            val fragment = NotesFragment().apply {
                arguments = Bundle().apply {
                    putString("SUBTOPIC_NAME", subtopicName)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
