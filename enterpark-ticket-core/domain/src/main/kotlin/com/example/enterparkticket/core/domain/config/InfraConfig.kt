package com.example.enterparkticket.core.domain.config

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfigGroup.*
import org.springframework.context.annotation.Configuration

@Configuration
@EnableEnterparkTicketConfig([JPA, REDIS])
class InfraConfig {
}