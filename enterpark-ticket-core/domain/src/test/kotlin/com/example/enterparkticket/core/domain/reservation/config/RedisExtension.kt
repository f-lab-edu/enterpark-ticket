package com.example.enterparkticket.core.domain.reservation.config

import io.kotest.core.extensions.MountableExtension
import org.testcontainers.containers.GenericContainer

class RedisExtension : MountableExtension<Unit, GenericContainer<*>> {

    override fun mount(configure: Unit.() -> Unit): GenericContainer<*> {
        return GenericContainer(IMAGE_NAME).apply {
            startupAttempts = 1
            withExposedPorts(REDIS_PORT)
        }
    }

    companion object {
        const val REDIS_PORT = 6379
        const val IMAGE_NAME = "redis:latest"
    }
}
