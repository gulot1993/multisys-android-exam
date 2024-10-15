package com.android.multisys.exam.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<VS: Any> : ViewModel() {
    protected val mutableUiState: MutableStateFlow<VS> by lazy {
        MutableStateFlow(initialState)
    }

    protected abstract val initialState: VS

    val uiState: StateFlow<VS> by lazy { mutableUiState.asStateFlow() }
}