package com.example.enterparkticket.apis.enduser.config

import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthDetails
import com.example.enterparkticket.core.domain.config.jpa.AuditorProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SecurityAuditorProvider : AuditorProvider {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map { it.authentication }
            .filter { it.isAuthenticated }
            .map {
                val principal = it.principal
                if (principal is AuthDetails) {
                    principal.username
                } else {
                    principal.toString()
                }
            }
    }
}
