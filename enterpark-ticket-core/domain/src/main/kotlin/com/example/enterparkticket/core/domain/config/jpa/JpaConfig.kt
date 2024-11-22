package com.example.enterparkticket.core.domain.config.jpa

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfig
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.enterparkticket"])
@EnableJpaRepositories(basePackages = ["com.example.enterparkticket"])
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
class JpaConfig : EnterparkTicketConfig {

    @Bean
    fun securityAuditorAware(auditorProvider: AuditorProvider): AuditorAware<String> {
        return SecurityAuditorAware(auditorProvider)
    }
}