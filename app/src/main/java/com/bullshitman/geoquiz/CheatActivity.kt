package com.bullshitman.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.security.AccessControlContext

private const val EXTRA_ANSWER_IS_TRUE = "answer_is_true"
const val EXTRA_ANSWER_SHOWN = "answer_shown"
const val EXTRA_NUMBER_CHEATS = "number_of_cheats"

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var apiVersionTextView: TextView
    private var answerIsTrue = false
    private var cheatCounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatCounter = intent.getIntExtra(EXTRA_NUMBER_CHEATS, 0)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        apiVersionTextView = findViewById(R.id.api_version_text_view)
        showAnswerButton.setOnClickListener {
            val answerText = when  {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
            apiVersionTextView.text = "API Level: ${Build.VERSION.SDK_INT} + ${cheatCounter+1}"
        }

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean, cheatCounter: Int): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_NUMBER_CHEATS, cheatCounter)
            }
        }
    }
}