package com.bullshitman.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"
class CheatViewModel() : ViewModel() {
    init {
        Log.d(TAG, "CheatViewModel instance created")
    }
    var isCheater = false
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "CheatViewModel instance about to be destroyed")
    }
}