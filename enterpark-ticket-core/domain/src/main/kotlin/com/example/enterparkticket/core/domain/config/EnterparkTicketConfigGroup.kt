package com.example.enterparkticket.core.domain.config

import com.example.enterparkticket.core.domain.config.jpa.JpaConfig

enum class EnterparkTicketConfigGroup(val configClass: Class<out EnterparkTicketConfig>) {
    JPA(JpaConfig::class.java),
}
