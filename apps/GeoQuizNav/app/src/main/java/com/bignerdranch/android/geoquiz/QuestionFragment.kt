package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(),CheatFragment.CheatFragmentCallback {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)

        updateQuestion()

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            (activity as? QuestionActions)?.setIsCheater(false) // Reset the isCheater flag when moving to the next question
            updateQuestion()
        }



        binding.cheatButton.setOnClickListener {
            val action = QuestionFragmentDirections.actionQuestionFragmentToCheatFragment(questionBank[currentIndex].answer)
            findNavController().navigate(action)
            (activity as? QuestionActions)?.setIsCheater(true)
        }

        return binding.root
    }

    private fun updateQuestion() {
        binding.questionText.setText(questionBank[currentIndex].testResId)
    }

    override fun onCheatActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == REQUEST_CODE_CHEAT) {
            val didCheat = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            (activity as? QuestionActions)?.setIsCheater(didCheat)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val didCheat = (activity as? QuestionActions)?.getIsCheater() ?: false
        val resId = when {
            didCheat -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show()
    }


    interface QuestionActions {
        fun onCheatRequested()
        fun getIsCheater(): Boolean
        fun setIsCheater(value: Boolean)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}