package com.example.enterparkticket.apis.enduser.config.oauth.client

import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoTokenResponse
import com.example.enterparkticket.apis.enduser.config.oauth.dto.KakaoUserInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromFormData
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class KakaoClient(
    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private val redirectUri: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.client-secret}")
    private val clientSecret: String
) {

    private val authClient = WebClient.create("https://kauth.kakao.com")
    private val apiClient = WebClient.create("https://kapi.kakao.com")

    suspend fun getToken(code: String): KakaoTokenResponse { // 토큰 받기
        return authClient.post()
            .uri("/oauth/token")
            .header(
                HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_FORM_URLENCODED_VALUE
            )
            .body(
                fromFormData("grant_type", "authorization_code")
                    .with("client_id", clientId)
                    .with("redirect_uri", redirectUri)
                    .with("code", code)
                    .with("client_secret", clientSecret)
            )
            .retrieve()
            .awaitBody<KakaoTokenResponse>()
    }

    suspend fun getUserInfo(accessToken: String): KakaoUserInfoResponse { // 사용자 정보 가져오기
        return apiClient.get()
            .uri("/v2/user/me")
            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .retrieve()
            .awaitBody<KakaoUserInfoResponse>()
    }
}
