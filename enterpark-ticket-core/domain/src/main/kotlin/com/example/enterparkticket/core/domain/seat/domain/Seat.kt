package com.example.enterparkticket.core.domain.seat.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import com.example.enterparkticket.core.domain.seat.exception.AlreadyReservedSeatException
import jakarta.persistence.*

@Entity
class Seat(

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var grade: GradeType,

    @Column(nullable = false, length = 15)
    var seatNumber: String,

    @Column(nullable = false)
    var price: Int,

    @Column(nullable = false)
    var performanceId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    var place: Place? = null,

    @Column(nullable = false)
    var isReserved: Boolean = false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    var id: Long? = null,
) : BaseEntity() {

    fun validateIsReserved() {
        if (isReserved) {
            throw AlreadyReservedSeatException()
        }
    }
}
