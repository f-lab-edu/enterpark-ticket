package com.example.enterparkticket.core.domain.performance.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Schedule(

    @Column(nullable = false)
    var dateTime: LocalDateTime,

    @Column(nullable = false)
    var sequence: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    var performance: Performance? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    var id: Long? = null,
) : BaseEntity()
