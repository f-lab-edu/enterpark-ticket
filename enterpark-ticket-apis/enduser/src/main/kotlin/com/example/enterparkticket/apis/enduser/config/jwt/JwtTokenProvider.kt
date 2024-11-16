package com.example.enterparkticket.apis.enduser.config.jwt

import com.example.enterparkticket.apis.enduser.config.jwt.dto.AccessTokenInfo
import com.example.enterparkticket.apis.enduser.config.jwt.dto.JwtToken
import com.example.enterparkticket.core.domain.common.exeption.ExpiredTokenException
import com.example.enterparkticket.core.domain.common.exeption.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${spring.security.auth.jwt.secret-key}")
    private val secretKey: String,
    @Value("\${spring.security.auth.jwt.access-exp}")
    private val accessExp: Long,
    @Value("\${spring.security.auth.jwt.refresh-exp}")
    private val refreshExp: Long,
) {

    fun createToken(id: Long?, role: String): JwtToken {
        val now = Instant.now()
        val accessToken = Jwts.builder()
            .subject(id.toString())
            .claim(TOKEN_TYPE, ACCESS_TOKEN)
            .claim(TOKEN_ROLE, role)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(accessExp)))
            .signWith(getSecretKey())
            .compact()
        val refreshToken = Jwts.builder()
            .subject(id.toString())
            .claim(TOKEN_TYPE, REFRESH_TOKEN)
            .expiration(Date.from(now.plusSeconds(refreshExp)))
            .signWith(getSecretKey())
            .compact()
        return JwtToken.of(accessToken, refreshToken)
    }

    fun parseAccessToken(token: String): AccessTokenInfo {
        if (isAccessToken(token)) {
            val claims = getClaims(token)
            if (claims != null) {
                return AccessTokenInfo.of(claims)
            }
        }
        throw InvalidTokenException()
    }

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser()
                .verifyWith(getSecretKey() as SecretKey)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    private fun getSecretKey(): Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    private fun isAccessToken(token: String): Boolean {
        return getClaims(token)?.get(TOKEN_TYPE) == ACCESS_TOKEN
    }

    companion object {
        const val TOKEN_TYPE = "type"
        const val TOKEN_ROLE = "role"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }
}