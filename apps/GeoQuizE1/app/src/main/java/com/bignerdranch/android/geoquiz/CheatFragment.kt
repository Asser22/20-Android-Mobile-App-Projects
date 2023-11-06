// CheatFragment.kt
package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.geoquiz.databinding.FragmentCheatBinding

class CheatFragment : Fragment() {
    private var _binding: FragmentCheatBinding? = null
    private val binding get() = _binding!!
    private var answer = false
    private var callback: CheatFragmentCallback? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? CheatFragmentCallback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCheatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Using Safe Args to retrieve the answer
        val args = CheatFragmentArgs.fromBundle(requireArguments())
        answer = args.answerKey

        binding.showAnswerButton.setOnClickListener {
            val textVal = when {
                answer -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(textVal)

            val resultIntent = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true) // This line assumes the user cheated. Adjust as necessary.
            }
            callback?.onCheatActivityResult(REQUEST_CODE_CHEAT, Activity.RESULT_OK, resultIntent)
        }

    }
    interface CheatFragmentCallback {
        fun onCheatActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onDetach() {
        super.onDetach()
        callback = null
    }

}