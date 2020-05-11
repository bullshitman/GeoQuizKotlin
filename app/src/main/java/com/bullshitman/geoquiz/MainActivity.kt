package com.bullshitman.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questonBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        prevButton = findViewById(R.id.previous_button)
        showHide(prevButton)
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questonBank.size
            updateQuestion()
        }
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questonBank.size
            updateQuestion()
        }
        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questonBank.size
            updateQuestion()
        }
        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId = questonBank[currentIndex].textResID
        questionTextView.setText(questionTextResId)
        showHide(prevButton)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questonBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    private fun showHide(view:View) {
        view.visibility = if (currentIndex == 0){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }
}