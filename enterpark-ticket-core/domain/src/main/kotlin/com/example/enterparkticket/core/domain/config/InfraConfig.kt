package com.example.enterparkticket.core.domain.config

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfigGroup.*
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!test")
@Configuration
@EnableEnterparkTicketConfig([JPA, REDIS, ASYNC])
class InfraConfig
