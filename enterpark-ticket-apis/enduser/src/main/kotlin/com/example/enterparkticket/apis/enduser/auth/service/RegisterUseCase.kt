package com.example.enterparkticket.apis.enduser.auth.service

import com.example.enterparkticket.apis.enduser.auth.dto.request.RegisterUserRequest
import com.example.enterparkticket.apis.enduser.auth.dto.response.RegisterUserResponse
import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenProvider
import com.example.enterparkticket.apis.enduser.config.oauth.client.KakaoClient
import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.OAuthProvider
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class RegisterUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoClient: KakaoClient,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    suspend fun registerUser(code: String, request: RegisterUserRequest): RegisterUserResponse {
        val accessToken = kakaoClient.getToken(code).accessToken
        val userInfo = kakaoClient.getUserInfo(accessToken)
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
