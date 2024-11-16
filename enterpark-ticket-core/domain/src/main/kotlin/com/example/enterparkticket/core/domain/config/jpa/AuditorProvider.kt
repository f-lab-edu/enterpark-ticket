package com.example.enterparkticket.core.domain.config.jpa

interface AuditorProvider {

    fun getCurrentAuditor(): String?
}