package com.example.enterparkticket.core.domain.user.service

import com.example.enterparkticket.core.domain.user.domain.User
import com.example.enterparkticket.core.domain.user.repository.UserRepository
import com.example.enterparkticket.core.domain.user.service.dto.RegisterUserDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDomainService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(oid: Long, email: String, dto: RegisterUserDto): User {
        val user = dto.toUserEntity(oid, email)
        userRepository.save(user)
        return user
    }
}
