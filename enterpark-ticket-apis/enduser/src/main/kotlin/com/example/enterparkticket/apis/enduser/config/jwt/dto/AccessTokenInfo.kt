package com.example.enterparkticket.apis.enduser.config.jwt.dto

import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenProvider.Companion.TOKEN_ROLE
import io.jsonwebtoken.Claims

data class AccessTokenInfo(
    val userId: Long,
    val role: String,
) {

    companion object {

        fun of(claims: Claims): AccessTokenInfo {
            return AccessTokenInfo(claims.subject.toLong(), claims[TOKEN_ROLE].toString())
        }
    }
}