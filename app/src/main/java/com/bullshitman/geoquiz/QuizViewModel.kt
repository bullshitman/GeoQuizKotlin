package com.bullshitman.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel(private var currentQuestionIndex: Int = 0) : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }
    private val questonBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    var currentIndex = 0
    val currentQuestionAnswer: Boolean
        get() = questonBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questonBank[currentIndex].textResID
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questonBank.size
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}