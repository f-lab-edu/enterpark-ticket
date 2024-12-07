package com.example.enterparkticket.apis.enduser.user.service

import com.example.enterparkticket.apis.enduser.auth.service.helper.KakaoOAuthHelper
import com.example.enterparkticket.apis.enduser.user.dto.request.UpdateUserRequest
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class UpdateUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoOAuthHelper: KakaoOAuthHelper,
) {

    fun updateUser(userId: Long, request: UpdateUserRequest) {
        val user = userDomainService.findUserById(userId)
        val accessToken = kakaoOAuthHelper.getToken(user.oAuthInfo.oid)
        val userInfo = kakaoOAuthHelper.getUserInfo(accessToken)
        userDomainService.updateUser(userId, request.toUpdateUserDto(userInfo.kakaoAccount.email))
    }
}
