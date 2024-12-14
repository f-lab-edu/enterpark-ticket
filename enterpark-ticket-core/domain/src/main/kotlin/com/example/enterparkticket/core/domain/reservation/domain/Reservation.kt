package com.example.enterparkticket.core.domain.reservation.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
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

    // todo 결제 정보

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    var id: Long? = null,
) : BaseEntity()
