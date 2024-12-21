package com.example.enterparkticket.core.domain.config.redis

import com.example.enterparkticket.core.domain.config.EnterparkTicketConfig
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.enterparkticket"])
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int,
    @Value("\${spring.data.redis.password}")
    private val password: String,
) : EnterparkTicketConfig {

    @Bean
    @Primary
    fun customRedisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port).apply {
            password = RedisPassword.of(this@RedisConfig.password)
        }
        return LettuceConnectionFactory(config)
    }

    @Bean
    fun customRedisTemplate(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            connectionFactory = customRedisConnectionFactory()
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
        }
    }

    @Bean
    @Primary
    fun redissonClient(): RedissonClient {
        val config = Config().apply {
            useSingleServer()
                .setAddress("$REDISSON_HOST_PREFIX$host:$port")
                .setPassword(password)
        }
        return Redisson.create(config)
    }

    companion object {
        const val REDISSON_HOST_PREFIX = "redis://"
    }
}
