package com.example.enterparkticket.apis.enduser.config.oauth.dto

import com.example.enterparkticket.core.domain.user.service.dto.KakaoTokenDto
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoTokenResponse(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
) {

    fun toKakaoTokenDto(): KakaoTokenDto {
        return KakaoTokenDto(tokenType, accessToken, expiresIn, refreshToken, refreshTokenExpiresIn)
    }
}
