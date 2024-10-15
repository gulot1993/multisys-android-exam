package com.android.multisys.exam.feature.auth.state

data class AuthState(
    val isLoading: Boolean = true,
    val accessToken: String = ""
)
