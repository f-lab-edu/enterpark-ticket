package com.example.enterparkticket.apis.enduser.auth.service

import com.example.enterparkticket.apis.enduser.auth.dto.request.RegisterUserRequest
import com.example.enterparkticket.apis.enduser.auth.dto.response.RegisterUserResponse
import com.example.enterparkticket.apis.enduser.auth.service.helper.KakaoOAuthHelper
import com.example.enterparkticket.apis.enduser.config.jwt.JwtTokenProvider
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class RegisterUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoOAuthHelper: KakaoOAuthHelper,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun registerUser(code: String, request: RegisterUserRequest): RegisterUserResponse {
        val dto = kakaoOAuthHelper.getOAuthInfoEmail(code)
        val user = userDomainService.registerUser(
            dto.oAuthInfo, dto.email, request.toRegisterUserDto()
        )
        val jwtToken = jwtTokenProvider.createToken(user.id, user.role.name)
        return RegisterUserResponse.of(jwtToken)
    }
}
