package com.example.enterparkticket.batch.config

import com.example.enterparkticket.core.domain.config.jpa.AuditorProvider
import com.example.enterparkticket.core.domain.user.domain.RoleType
import org.springframework.stereotype.Component

@Component
class BatchAuditorProvider : AuditorProvider {

    override fun getCurrentAuditor(): String? {
        return RoleType.ADMIN.name
    }
}
