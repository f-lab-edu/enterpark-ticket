package com.example.enterparkticket.core.domain.config

enum class EnterparkTicketConfigGroup(val configClass: Class<out EnterparkTicketConfig>) {
    JPA(JpaConfig::class.java),
}
