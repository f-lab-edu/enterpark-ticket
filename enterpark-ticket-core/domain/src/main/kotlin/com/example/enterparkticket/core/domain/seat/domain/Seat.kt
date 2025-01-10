package com.example.enterparkticket.core.domain.seat.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import com.example.enterparkticket.core.domain.seat.exception.AlreadyReservedSeatException
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(
        name = "unique_grade_seat_id_seat_number",
        columnNames = ["grade_seat_id", "seat_number"],
    )]
)
class Seat(

    @Column(nullable = false, length = 15)
    var seatNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_seat_id")
    var gradeSeat: GradeSeat? = null,

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
