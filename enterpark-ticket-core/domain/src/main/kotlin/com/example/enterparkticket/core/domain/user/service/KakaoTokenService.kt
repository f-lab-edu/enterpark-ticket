package com.example.enterparkticket.core.domain.user.service

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.KAKAO_TOKEN_PREFIX
import com.example.enterparkticket.core.domain.user.exception.NotFoundKakaoTokenException
import com.example.enterparkticket.core.domain.user.service.dto.KakaoTokenDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class KakaoTokenService(private val redisTemplate: RedisTemplate<String, String>) {

    fun saveToken(oAuthId: Long, dto: KakaoTokenDto) {
        val key = KAKAO_TOKEN_PREFIX + oAuthId
        redisTemplate.opsForValue()
            .set(key, dto.accessToken, dto.expiresIn * 1000L, TimeUnit.MILLISECONDS)
    }

    fun getToken(oAuthId: Long): String {
        val key = KAKAO_TOKEN_PREFIX + oAuthId
        val value = redisTemplate.opsForValue()[key]
        return if (!value.isNullOrEmpty()) {
            value
        } else {
            throw NotFoundKakaoTokenException()
        }
    }
}
