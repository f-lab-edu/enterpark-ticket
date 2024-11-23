package com.example.enterparkticket.apis.enduser.auth.service.helper

import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoTokenClient
import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoUserClient
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoOAuthInfoEmailDto
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenRequest
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUserInfoResponse
import com.example.enterparkticket.apis.enduser.config.oauth.properties.KakaoOAuthProperties
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.BEARER
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.KAKAO_AK
import com.example.enterparkticket.core.domain.common.exeption.InvalidEmailException
import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.OAuthProvider
import com.example.enterparkticket.core.domain.user.service.KakaoTokenService
import org.springframework.stereotype.Service

@Service
class KakaoOAuthHelper(
    private val kakaoOAuthProperties: KakaoOAuthProperties,
    private val kakaoTokenClient: KakaoTokenClient,
    private val kakaoUserClient: KakaoUserClient,
    private val kakaoTokenService: KakaoTokenService,
) {

    fun getOAuthInfoEmail(code: String): KakaoOAuthInfoEmailDto {
        val request = KakaoTokenRequest.of(kakaoOAuthProperties, code)
        val token = kakaoTokenClient.getToken(request)
        val userInfo = getUserInfo(token.accessToken)
        val oAuthInfo = OAuthInfo.of(OAuthProvider.KAKAO, userInfo.id)
        kakaoTokenService.saveToken(oAuthInfo.oid, token.toKakaoTokenDto())
        return KakaoOAuthInfoEmailDto.of(oAuthInfo, userInfo.kakaoAccount.email)
    }

    fun getToken(oAuthId: Long): String {
        return kakaoTokenService.getToken(oAuthId)
    }

    fun getUserInfo(accessToken: String): KakaoUserInfoResponse {
        val userInfo = kakaoUserClient.getUserInfo(BEARER + accessToken)
        validateEmail(userInfo)
        return userInfo
    }

    fun unlinkUser(targetId: Long) {
        kakaoUserClient.unlinkUser(KAKAO_AK + kakaoOAuthProperties.adminKey, targetId)
        kakaoTokenService.deleteToken(targetId)
    }

    private fun validateEmail(userInfo: KakaoUserInfoResponse) {
        if (!userInfo.kakaoAccount.isEmailValid || !userInfo.kakaoAccount.isEmailVerified) {
            throw InvalidEmailException()
        }
    }
}
