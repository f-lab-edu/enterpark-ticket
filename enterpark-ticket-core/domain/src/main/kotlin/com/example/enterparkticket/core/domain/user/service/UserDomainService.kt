package com.example.enterparkticket.core.domain.user.service

import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.User
import com.example.enterparkticket.core.domain.user.repository.UserRepository
import com.example.enterparkticket.core.domain.user.service.dto.RegisterUserDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDomainService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(oAuthInfo: OAuthInfo, email: String, dto: RegisterUserDto): User {
        val user = dto.toUserEntity(oAuthInfo, email)
        userRepository.save(user)
        return user
    }
}
