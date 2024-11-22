package com.example.enterparkticket.apis.enduser.config

import feign.Logger.Level
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [BaseFeignClientsPackage::class])
class FeignConfig {

    @Bean
    fun feignLoggerLevel(): Level {
        return Level.FULL
    }
}
