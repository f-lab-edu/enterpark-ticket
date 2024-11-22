package com.example.enterparkticket.apis.enduser.config

import com.example.enterparkticket.apis.enduser.config.jwt.dto.AuthDetails
import com.example.enterparkticket.core.domain.config.jpa.AuditorProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityAuditorProvider : AuditorProvider {

    override fun getCurrentAuditor(): String? {
        return SecurityContextHolder.getContext().authentication
            ?.takeIf { it.isAuthenticated }
            ?.principal
            ?.let { principal ->
                if (principal is AuthDetails) {
                    principal.username
                } else {
                    principal.toString()
                }
            }
    }
}
