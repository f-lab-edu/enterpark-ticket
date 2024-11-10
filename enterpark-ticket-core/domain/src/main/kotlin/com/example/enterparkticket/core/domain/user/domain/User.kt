package com.example.enterparkticket.core.domain.user.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class User(

    @Column(unique = true)
    var oid: Long, // 회원 번호

    var name: String,

    var email: String,

    var phoneNumber: String,

    var birthDate: LocalDate,

    @Enumerated(value = EnumType.STRING)
    var gender: GenderType,

    var address: String? = null,

    @Enumerated(value = EnumType.STRING)
    var role: RoleType = RoleType.USER,

    @Enumerated(value = EnumType.STRING)
    var state: StateType = StateType.NORMAL,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null,
)
