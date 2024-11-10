package com.example.enterparkticket.apis.enduser.auth.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class KakaoLoginController(
    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private val redirectUri: String,
) {

    @RequestMapping("/login")
    fun kakaoLogin(model: Model): String {
        val location =
            "https://kauth.kakao.com/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUri&response_type=code"
        model.addAttribute("location", location)
        return "login"
    }
}
