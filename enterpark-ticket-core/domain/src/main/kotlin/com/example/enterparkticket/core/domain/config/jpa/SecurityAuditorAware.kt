package com.example.enterparkticket.core.domain.config.jpa

import org.springframework.data.domain.AuditorAware
import java.util.*

class SecurityAuditorAware(private val auditorProvider: AuditorProvider) : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable(auditorProvider.getCurrentAuditor())
    }
}
