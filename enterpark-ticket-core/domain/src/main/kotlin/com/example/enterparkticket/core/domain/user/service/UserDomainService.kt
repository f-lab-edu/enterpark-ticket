package com.example.enterparkticket.core.domain.user.service

import com.example.enterparkticket.core.domain.user.domain.OAuthInfo
import com.example.enterparkticket.core.domain.user.domain.User
import com.example.enterparkticket.core.domain.user.exception.AlreadyRegisterUserException
import com.example.enterparkticket.core.domain.user.exception.NotFoundUserException
import com.example.enterparkticket.core.domain.user.repository.UserRepository
import com.example.enterparkticket.core.domain.user.service.dto.RegisterUserDto
import com.example.enterparkticket.core.domain.user.service.dto.UpdateUserDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserDomainService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(oAuthInfo: OAuthInfo, email: String, dto: RegisterUserDto): User {
        validateUser(oAuthInfo)
        val user = dto.toUserEntity(oAuthInfo, email)
        userRepository.save(user)
        return user
    }

    @Transactional
    fun updateUser(userId: Long, dto: UpdateUserDto) {
        val user = findUserById(userId)
        user.updateUser(dto.name, dto.email, dto.phoneNumber, dto.address)
    }

    fun findUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            NotFoundUserException()
        }
    }

    private fun validateUser(oAuthInfo: OAuthInfo) {
        if (userRepository.existsByOAuthInfo(oAuthInfo)) {
            throw AlreadyRegisterUserException()
        }
    }
}
