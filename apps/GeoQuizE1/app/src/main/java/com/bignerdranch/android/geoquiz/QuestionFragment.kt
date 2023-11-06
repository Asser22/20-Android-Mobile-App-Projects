package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    // Updated Question data class to include user's answer
    data class Question(val textResId: Int, val answer: Boolean, var userAnswer: Boolean? = null)

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        // Add new questions here
        Question(R.string.question_europe, true),
        Question(R.string.question_antarctica, true),
        Question(R.string.question_north_america, true),
        Question(R.string.question_south_america, false),
        Question(R.string.question_russia, true),
        Question(R.string.question_asia_rivers, true),
        Question(R.string.question_africa_desert, true),
        Question(R.string.question_europe_union, false),
        Question(R.string.question_middle_east, true),
        Question(R.string.question_global_population, true)
    )

    private var currentIndex = 0
    private var correctAnswersCount = 0
    private var isCheater = false
    private var answerState = BooleanArray(questionBank.size) { false }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            goToNextQuestion()
        }

        binding.prevButton.setOnClickListener {
            goToPreviousQuestion()
        }

        binding.cheatButton.setOnClickListener {
            // Handle cheating here
            isCheater = true
        }

        binding.questionText.setOnClickListener {
            goToNextQuestion()
        }

        updateQuestion()
        return binding.root
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        binding.questionText.setText(question)
        binding.trueButton.isEnabled = !answerState[currentIndex]
        binding.falseButton.isEnabled = !answerState[currentIndex]
    }

    private fun goToNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        isCheater = false
        updateQuestion()
    }

    private fun goToPreviousQuestion() {
        currentIndex = if (currentIndex - 1 >= 0) currentIndex - 1 else questionBank.size - 1
        updateQuestion()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        answerState[currentIndex] = true

        val messageResId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> {
                correctAnswersCount++
                R.string.correct_toast
            }
            else -> R.string.incorrect_toast
        }

        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_SHORT).show()
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        if (answerState.all { it }) {
            val score = correctAnswersCount * 100 / questionBank.size
            Toast.makeText(
                requireContext(),
                getString(R.string.quiz_score, correctAnswersCount, questionBank.size, score),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

