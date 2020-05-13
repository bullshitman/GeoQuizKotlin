package com.bullshitman.geoquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizViewModelFactory(private val currentQuestionIndex: Int = 0) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(currentQuestionIndex) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}