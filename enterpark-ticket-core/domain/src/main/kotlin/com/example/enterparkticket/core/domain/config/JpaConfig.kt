package com.example.enterparkticket.core.domain.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.enterparkticket"])
@EnableJpaRepositories(basePackages = ["com.example.enterparkticket"])
class JpaConfig : EnterparkTicketConfig {
}