package com.example.enterparkticket.core.domain.config

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(EnterparkTicketConfigImportSelector::class)
annotation class EnableEnterparkTicketConfig(val value: Array<EnterparkTicketConfigGroup>)
