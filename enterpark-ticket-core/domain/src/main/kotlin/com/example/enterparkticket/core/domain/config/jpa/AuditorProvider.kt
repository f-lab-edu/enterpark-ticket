package com.example.enterparkticket.core.domain.config.jpa

import java.util.*

interface AuditorProvider {

    fun getCurrentAuditor(): Optional<String>
}