package com.android.multisys.exam.data.dto

import com.android.multisys.exam.base.BaseModel
import com.android.multisys.exam.data.domain.AuthResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponseDTO(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String
) : BaseModel {
    companion object {
        fun AuthResponseDTO.toDomain(): AuthResponse {
            return AuthResponse(
                accessToken = accessToken,
                tokenType = tokenType
            )
        }
    }
}
