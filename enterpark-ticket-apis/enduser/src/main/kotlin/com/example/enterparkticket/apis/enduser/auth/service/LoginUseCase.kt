package com.example.enterparkticket.apis.enduser.auth.service

import com.example.enterparkticket.apis.enduser.auth.dto.response.UserTokenResponse
import com.example.enterparkticket.apis.enduser.auth.service.helper.KakaoOAuthHelper
import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenProvider
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class LoginUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoOAuthHelper: KakaoOAuthHelper,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun loginUser(code: String): UserTokenResponse {
        val oAuthInfo = kakaoOAuthHelper.getOAuthInfoEmail(code).oAuthInfo
        val user = userDomainService.loginUser(oAuthInfo)
        val jwtToken = jwtTokenProvider.createToken(user.id, user.role.name)
        return UserTokenResponse.of(jwtToken)
    }
}
