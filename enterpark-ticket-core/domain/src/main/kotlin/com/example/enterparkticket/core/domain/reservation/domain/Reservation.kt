package com.example.enterparkticket.core.domain.reservation.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(
    indexes = [Index(
        name = "idx_user_id_performance_id",
        columnList = "user_id, performance_id",
    )]
)
class Reservation(

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var state: StateType,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var ticketReceipt: TicketReceiptType,

    @Column(nullable = false)
    var userId: Long,

    @Column(nullable = false)
    var performanceId: Long,

    @Column(nullable = false)
    var seatId: Long?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    var id: Long? = null,
) : BaseEntity()
