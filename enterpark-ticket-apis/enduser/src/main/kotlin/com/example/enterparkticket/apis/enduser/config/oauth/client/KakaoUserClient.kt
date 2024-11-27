package com.example.enterparkticket.apis.enduser.config.oauth.client

import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUnlinkUserResponse
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "KakaoUserClient", url = "https://kapi.kakao.com")
interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): KakaoUserInfoResponse

    @PostMapping("/v1/user/unlink")
    fun unlinkUser(
        @RequestHeader("Authorization") adminKey: String,
        @RequestParam("target_id") targetId: Long,
        @RequestParam("target_id_type") targetIdType: String = "user_id",
    ): KakaoUnlinkUserResponse
}
