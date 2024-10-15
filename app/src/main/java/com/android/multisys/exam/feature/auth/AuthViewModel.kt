package com.android.multisys.exam.feature.auth

import com.android.multisys.exam.base.BaseViewModel
import com.android.multisys.exam.feature.auth.state.AuthState
import com.android.multisys.exam.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): BaseViewModel<AuthState>() {
    @Inject
    lateinit var repository: RedditRepository
    override val initialState: AuthState
        get() = AuthState()
}