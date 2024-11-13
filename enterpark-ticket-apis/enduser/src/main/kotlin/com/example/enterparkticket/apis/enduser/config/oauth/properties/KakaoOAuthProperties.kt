package com.example.enterparkticket.apis.enduser.config.oauth.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.oauth2.client.registration.kakao")
data class KakaoOAuthProperties(
    val clientId: String,
    val redirectUri: String,
    val clientSecret: String
)
