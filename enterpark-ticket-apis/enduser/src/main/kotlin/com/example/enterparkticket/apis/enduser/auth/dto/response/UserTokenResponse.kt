package com.example.enterparkticket.apis.enduser.auth.dto.response

import com.example.enterparkticket.apis.enduser.config.jwt.dto.JwtToken

data class UserTokenResponse(
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {

        fun of(jwtToken: JwtToken): UserTokenResponse {
            return UserTokenResponse(jwtToken.accessToken, jwtToken.refreshToken)
        }
    }
}
