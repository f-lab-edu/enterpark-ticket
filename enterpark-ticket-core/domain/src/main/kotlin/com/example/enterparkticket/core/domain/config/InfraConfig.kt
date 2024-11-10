package com.example.enterparkticket.core.domain.config

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfigGroup.JPA
import org.springframework.context.annotation.Configuration

@Configuration
@EnableEnterparkTicketConfig([JPA])
class InfraConfig {
}