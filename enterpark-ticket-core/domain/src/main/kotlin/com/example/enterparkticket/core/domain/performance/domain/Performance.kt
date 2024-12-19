package com.example.enterparkticket.core.domain.performance.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Performance(

    @Column(nullable = false, length = 255)
    var title: String,

    @Column(nullable = false, length = 255)
    var imageUrl: String,

    @Column(nullable = false, length = 255)
    var description: String,

    @Column(nullable = false)
    var startDate: LocalDate,

    @Column(nullable = false)
    var endDate: LocalDate,

    @Column(nullable = false)
    var totalTime: Int,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 15)
    var ageLimit: AgeLimitType,

    @Column(nullable = false)
    var placeId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    var id: Long? = null,
) : BaseEntity()
