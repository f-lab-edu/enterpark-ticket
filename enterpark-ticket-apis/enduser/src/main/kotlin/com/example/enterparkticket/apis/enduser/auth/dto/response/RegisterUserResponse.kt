package com.example.enterparkticket.apis.enduser.auth.dto.response

import com.example.enterparkticket.apis.enduser.config.jwt.dto.JwtToken

data class RegisterUserResponse(
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {

        fun of(jwtToken: JwtToken): RegisterUserResponse {
            return RegisterUserResponse(jwtToken.accessToken, jwtToken.refreshToken)
        }
    }
}
