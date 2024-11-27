package com.example.enterparkticket.core.domain.user.service.dto

data class KakaoTokenDto(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
)
