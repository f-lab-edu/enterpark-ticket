package com.example.enterparkticket.core.domain.common.aop

import com.example.enterparkticket.core.domain.common.aop.DistributedLock.Companion.LEASE_TIME
import com.example.enterparkticket.core.domain.common.aop.DistributedLock.Companion.WAIT_TIME
import com.example.enterparkticket.core.domain.common.aop.DistributedLock.Companion.createKey
import com.example.enterparkticket.core.domain.common.aop.DistributedLock.Companion.redissonClient
import com.example.enterparkticket.core.domain.common.exeption.NotAvailableDistributedLockException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

fun <T> lock(vararg keys: String, function: () -> T): T {
    val rLock = redissonClient.getLock(createKey(keys))
    try {
        val available = rLock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS)
        if (!available) {
            throw NotAvailableDistributedLockException()
        }
        logger.info { "redisson lock 실행 ${createKey(keys)} 스레드 ${Thread.currentThread().id}" }
        return Transaction.propagationRequiresNew(function)
    } catch (e: InterruptedException) {
        throw e
    } finally {
        try {
            rLock.unlock()
        } catch (e: IllegalMonitorStateException) {
            throw e
        }
    }
}

@Component
class DistributedLock(client: RedissonClient) {

    init {
        redissonClient = client
    }

    companion object {
        lateinit var redissonClient: RedissonClient
            private set
        private const val SEPARATOR = ":"
        const val WAIT_TIME = 5L
        const val LEASE_TIME = 3L

        fun createKey(keys: Array<out Any>) = keys.joinToString(SEPARATOR)
    }
}
