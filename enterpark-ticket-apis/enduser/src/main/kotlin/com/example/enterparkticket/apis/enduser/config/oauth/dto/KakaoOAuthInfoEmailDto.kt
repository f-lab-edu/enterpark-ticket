package com.example.enterparkticket.apis.enduser.config.oauth.dto

import com.example.enterparkticket.core.domain.user.domain.OAuthInfo

data class KakaoOAuthInfoEmailDto(
    val oAuthInfo: OAuthInfo,
    val email: String,
) {

    companion object {

        fun of(oAuthInfo: OAuthInfo, email: String): KakaoOAuthInfoEmailDto {
            return KakaoOAuthInfoEmailDto(oAuthInfo, email)
        }
    }
}
