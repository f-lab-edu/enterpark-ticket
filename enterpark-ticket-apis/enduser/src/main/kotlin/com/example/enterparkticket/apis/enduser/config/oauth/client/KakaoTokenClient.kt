package com.example.enterparkticket.apis.enduser.config.oauth.client

import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenRequest
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "KakaoTokenClient", url = "https://kauth.kakao.com")
interface KakaoTokenClient {

    @PostMapping("/oauth/token", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(request: KakaoTokenRequest): KakaoTokenResponse
}
