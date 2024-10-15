package com.android.multisys.exam.data.domain

import com.android.multisys.exam.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponse(
    val accessToken: String,
    val tokenType: String
) : BaseModel
