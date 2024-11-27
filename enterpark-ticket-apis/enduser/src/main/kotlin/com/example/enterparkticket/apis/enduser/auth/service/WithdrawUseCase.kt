package com.example.enterparkticket.apis.enduser.auth.service

import com.example.enterparkticket.apis.enduser.auth.service.helper.KakaoOAuthHelper
import com.example.enterparkticket.core.domain.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class WithdrawUseCase(
    private val userDomainService: UserDomainService,
    private val kakaoOAuthHelper: KakaoOAuthHelper,
) {

    fun withdrawUser(userId: Long) {
        val user = userDomainService.withdrawUser(userId)
        kakaoOAuthHelper.unlinkUser(user.oAuthInfo.oid)
    }
}
