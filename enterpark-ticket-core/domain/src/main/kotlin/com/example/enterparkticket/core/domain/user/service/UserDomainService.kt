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
        validateUser(oAuthInfo)
        val user = dto.toUserEntity(oAuthInfo, email)
        userRepository.save(user)
        return user
    }

    private fun validateUser(oAuthInfo: OAuthInfo) {
        if (userRepository.existsByOAuthInfo(oAuthInfo)) {
            throw RuntimeException("이미 존재하는 회원입니다.") // todo exception
        }
    }
}
