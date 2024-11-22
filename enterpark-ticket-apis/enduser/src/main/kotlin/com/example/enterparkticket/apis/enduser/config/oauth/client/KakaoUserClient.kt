package com.example.enterparkticket.apis.enduser.config.oauth.client

import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "KakaoUserClient", url = "https://kapi.kakao.com")
interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): KakaoUserInfoResponse
}
