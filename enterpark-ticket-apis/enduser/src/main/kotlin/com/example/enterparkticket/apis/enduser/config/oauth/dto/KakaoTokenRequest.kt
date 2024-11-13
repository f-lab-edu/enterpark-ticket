package com.example.enterparkticket.apis.enduser.config.oauth.dto

import com.example.enterparkticket.apis.enduser.config.oauth.properties.KakaoOAuthProperties
import feign.form.FormProperty

data class KakaoTokenRequest(

    @FormProperty("client_id")
    var clientId: String,

    @FormProperty("redirect_uri")
    var redirectUri: String,

    var code: String,

    @FormProperty("client_secret")
    var clientSecret: String,

    @FormProperty("grant_type")
    var grantType: String = "authorization_code",
) {

    companion object {

        fun of(properties: KakaoOAuthProperties, code: String): KakaoTokenRequest {
            return KakaoTokenRequest(
                properties.clientId,
                properties.redirectUri,
                code,
                properties.clientSecret
            )
        }
    }
}
