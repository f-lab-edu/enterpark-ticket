package com.example.enterparkticket.core.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class OAuthInfo(

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    val provider: OAuthProvider,

    @Column(nullable = false)
    val oid: Long,
) {

    companion object {

        fun of(provider: OAuthProvider, oid: Long): OAuthInfo {
            return OAuthInfo(provider, oid)
        }
    }
}
