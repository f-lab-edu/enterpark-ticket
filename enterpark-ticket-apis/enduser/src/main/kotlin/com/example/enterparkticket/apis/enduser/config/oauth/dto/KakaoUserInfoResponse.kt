package com.example.enterparkticket.apis.enduser.config.oauth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoUserInfoResponse(
    val id: Long,
    val kakaoAccount: KakaoAccount,
)

@JsonNaming(SnakeCaseStrategy::class)
data class KakaoAccount(
    val isEmailValid: Boolean,
    val isEmailVerified: Boolean,
    val email: String,
)
