package com.example.enterparkticket.apis.enduser.config

import com.example.enterparkticket.apis.enduser.config.oauth.properties.KakaoOAuthProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(value = [KakaoOAuthProperties::class])
@Configuration
class ConfigurationPropertiesConfig {
}
