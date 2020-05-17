package com.bullshitman.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import java.security.AccessControlContext
private const val TAG = "CheatActivity"
private const val EXTRA_ANSWER_IS_TRUE = "answer_is_true"
const val EXTRA_ANSWER_SHOWN = "answer_shown"
private const val IS_CHEATER = "is_cheater"

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue = false
    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProvider(this@CheatActivity).get(CheatViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        val isCheater = savedInstanceState?.getBoolean(IS_CHEATER, false) ?: false
        cheatViewModel.isCheater = isCheater
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        if (cheatViewModel.isCheater) {
            cheatingResult()
        }
        showAnswerButton.setOnClickListener {
            cheatingResult()
        }
    }

    private fun cheatingResult() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        answerTextView.setText(answerText)
        cheatViewModel.isCheater = true
        setAnswerShownResult(true)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "cheater status: ${cheatViewModel.isCheater}")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean(IS_CHEATER, cheatViewModel.isCheater)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}