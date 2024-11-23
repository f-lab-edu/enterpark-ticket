package com.example.enterparkticket.core.domain.user.domain

import com.example.enterparkticket.core.domain.common.BaseTimeEntity
import com.example.enterparkticket.core.domain.user.exception.ReRegisterUserException
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(
        name = "PROVIDER_OID_UNIQUE",
        columnNames = ["provider", "oid"]
    )]
)
class User(

    @Embedded
    var oAuthInfo: OAuthInfo,

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
) : BaseTimeEntity() {

    fun updateUser(name: String, email: String, phoneNumber: String, address: String?) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.address = address
    }

    fun withdrawUser() {
        state = StateType.SUSPENDED
        deleteSoftly()
    }

    fun validateState() {
        if (state == StateType.SUSPENDED) {
            throw ReRegisterUserException()
        }
    }
}
