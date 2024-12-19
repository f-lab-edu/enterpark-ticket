package com.example.enterparkticket.core.domain.seat.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import com.example.enterparkticket.core.domain.performance.domain.Performance
import jakarta.persistence.*

@Entity
class GradeSeat(

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var grade: GradeType,

    @Column(nullable = false)
    var seatCount: Int,

    @Column(nullable = false)
    var price: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    var performance: Performance? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_seat_id")
    var id: Long? = null,
) : BaseEntity()
