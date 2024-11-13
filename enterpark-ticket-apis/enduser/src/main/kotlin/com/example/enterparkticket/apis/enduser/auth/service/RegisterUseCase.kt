package com.example.enterparkticket.apis.enduser.auth.service

import com.example.enterparkticket.apis.enduser.auth.dto.request.RegisterUserRequest
import com.example.enterparkticket.apis.enduser.auth.dto.response.RegisterUserResponse
import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenProvider
import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoUserClient
import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoTokenClient
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenRequest
import com.example.enterparkticket.apis.enduser.config.oauth.properties.KakaoOAuthProperties
import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.OAuthProvider
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class RegisterUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoOAuthProperties: KakaoOAuthProperties,
    private val kakaoTokenClient: KakaoTokenClient,
    private val kakaoUserClient: KakaoUserClient,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun registerUser(code: String, request: RegisterUserRequest): RegisterUserResponse {
        val kakaoTokenRequest = KakaoTokenRequest.of(kakaoOAuthProperties, code)
        val accessToken = kakaoTokenClient.getToken(kakaoTokenRequest).accessToken
        val userInfo = kakaoUserClient.getUserInfo("Bearer $accessToken")
        val oAuthInfo = OAuthInfo.of(OAuthProvider.KAKAO, userInfo.id)
        val user = userDomainService.registerUser(
            oAuthInfo,
            userInfo.kakaoAccount.email,
            request.toRegisterUserDto()
        )
        val jwtToken = jwtTokenProvider.createToken(user.id, user.role.name)
        return RegisterUserResponse.of(jwtToken)
    }
}
