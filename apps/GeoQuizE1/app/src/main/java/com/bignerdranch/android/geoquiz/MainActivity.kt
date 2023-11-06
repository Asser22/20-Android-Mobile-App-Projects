package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val EXTRA_ANSWER_KEY = "com.bignerdranch.android.geoquiz.answer_key"
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
const val REQUEST_CODE_CHEAT = 0


class MainActivity : AppCompatActivity(), CheatFragment.CheatFragmentCallback {

    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )
    private var currentIndex = 0
    private var isCheater = false

    fun onCheatRequested() {
        val fragment = CheatFragment().apply {
            arguments = Bundle().apply {
                putBoolean(EXTRA_ANSWER_KEY, questionBank[currentIndex].answer)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Provide the current isCheater state to the QuestionFragment
    fun getIsCheater(): Boolean {
        return isCheater
    }

    // Allow the QuestionFragment to update the isCheater state
    fun setIsCheater(value: Boolean) {
        isCheater = value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the navigation host
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val resId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        isCheater = false

        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    override fun onCheatActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return

        if (requestCode == REQUEST_CODE_CHEAT) {
            isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    // configuration changes.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }
    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
            super.onBackPressed()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
