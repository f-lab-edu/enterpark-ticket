package com.example.enterparkticket.apis.enduser.auth.service.helper

import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoTokenClient
import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoUserClient
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoOAuthInfoEmailDto
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenRequest
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUserInfoResponse
import com.example.enterparkticket.apis.enduser.config.oauth.properties.KakaoOAuthProperties
import com.example.enterparkticket.core.domain.common.exeption.InvalidEmailException
import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.OAuthProvider
import org.springframework.stereotype.Service

@Service
class KakaoOAuthHelper(
    private val kakaoOAuthProperties: KakaoOAuthProperties,
    private val kakaoTokenClient: KakaoTokenClient,
    private val kakaoUserClient: KakaoUserClient,
) {

    fun getOAuthInfoEmail(code: String): KakaoOAuthInfoEmailDto {
        val request = KakaoTokenRequest.of(kakaoOAuthProperties, code)
        val accessToken = kakaoTokenClient.getToken(request).accessToken
        val userInfo = getUserInfo(accessToken)
        val oAuthInfo = OAuthInfo.of(OAuthProvider.KAKAO, userInfo.id)
        return KakaoOAuthInfoEmailDto.of(oAuthInfo, userInfo.kakaoAccount.email)
    }

    private fun getUserInfo(accessToken: String): KakaoUserInfoResponse {
        val userInfo = kakaoUserClient.getUserInfo(BEARER + accessToken)
        validateEmail(userInfo)
        return userInfo
    }

    private fun validateEmail(userInfo: KakaoUserInfoResponse) {
        if (!userInfo.kakaoAccount.isEmailValid || !userInfo.kakaoAccount.isEmailVerified) {
            throw InvalidEmailException()
        }
    }

    companion object {
        const val BEARER = "Bearer "
    }
}
