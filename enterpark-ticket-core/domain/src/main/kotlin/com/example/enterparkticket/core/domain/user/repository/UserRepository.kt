package com.example.enterparkticket.core.domain.user.repository

import com.example.enterparkticket.core.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun existsByOid(oid: Long): Boolean
}
