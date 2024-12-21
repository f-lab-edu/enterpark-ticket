package com.example.enterparkticket.core.domain.config

import com.example.enterparkticket.core.domain.config.async.AsyncConfig
import com.example.enterparkticket.core.domain.config.jpa.JpaConfig
import com.example.enterparkticket.core.domain.config.redis.RedisConfig

enum class EnterparkTicketConfigGroup(val configClass: Class<out EnterparkTicketConfig>) {
    JPA(JpaConfig::class.java),
    REDIS(RedisConfig::class.java),
    ASYNC(AsyncConfig::class.java),
}
