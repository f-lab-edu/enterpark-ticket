package com.example.enterparkticket.apis.enduser.config.oauth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoTokenResponse(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
)
