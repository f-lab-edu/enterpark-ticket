package com.example.enterparkticket.core.domain.user.service.dto

import com.example.enterparkticket.core.domain.user.domain.GenderType
import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.User
import java.time.LocalDate

data class RegisterUserDto(
    val name: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    val gender: GenderType,
) {

    fun toUserEntity(oAuthInfo: OAuthInfo, email: String): User {
        return User(oAuthInfo, name, email, phoneNumber, birthDate, gender)
    }
}
