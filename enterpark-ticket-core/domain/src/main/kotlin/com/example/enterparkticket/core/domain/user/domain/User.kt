package com.example.enterparkticket.core.domain.user.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class User(

    @Column(unique = true)
    var oid: Long, // 회원 번호

    @Column(nullable = false, length = 20)
    var name: String,

    @Column(nullable = false, length = 255)
    var email: String,

    @Column(nullable = false, length = 20)
    var phoneNumber: String,

    @Column(nullable = false)
    var birthDate: LocalDate,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var gender: GenderType,

    @Column(nullable = true, length = 255)
    var address: String? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var role: RoleType = RoleType.USER,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var state: StateType = StateType.NORMAL,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null,
)
