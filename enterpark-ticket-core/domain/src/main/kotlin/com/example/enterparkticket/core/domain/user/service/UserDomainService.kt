package com.example.enterparkticket.core.domain.user.service

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.BATCH_TRANSACTION_MANAGER
import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.PRIMARY_TRANSACTION_MANAGER
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

    @Transactional(PRIMARY_TRANSACTION_MANAGER)
    fun registerUser(oAuthInfo: OAuthInfo, email: String, dto: RegisterUserDto): User {
        validateUser(oAuthInfo)
        val user = dto.toUserEntity(oAuthInfo, email)
        userRepository.save(user)
        return user
    }

    @Transactional(PRIMARY_TRANSACTION_MANAGER)
    fun loginUser(oAuthInfo: OAuthInfo): User {
        return userRepository.findByOAuthInfo(oAuthInfo) ?: throw NotFoundUserException()
    }

    @Transactional(PRIMARY_TRANSACTION_MANAGER)
    fun updateUser(userId: Long, dto: UpdateUserDto) {
        val user = findUserById(userId)
        user.updateUser(dto.name, dto.email, dto.phoneNumber, dto.address)
    }

    fun findUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            NotFoundUserException()
        }
    }

    @Transactional(PRIMARY_TRANSACTION_MANAGER)
    fun withdrawUser(userId: Long): User {
        val user = findUserById(userId)
        user.withdrawUser()
        return user
    }

    @Transactional(BATCH_TRANSACTION_MANAGER)
    fun deleteUser(user: User): User {
        return user.deleteUser()
    }

    private fun validateUser(oAuthInfo: OAuthInfo) {
        userRepository.findByOAuthInfo(oAuthInfo)?.let {
            it.validateState()
            throw AlreadyRegisterUserException()
        }
    }
}
