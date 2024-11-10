package com.example.enterparkticket.apis.enduser.config.jwt.dto

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {

        fun of(accessToken: String, refreshToken: String): JwtToken {
            return JwtToken(accessToken, refreshToken)
        }
    }
}