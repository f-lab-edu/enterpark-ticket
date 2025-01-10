package com.example.enterparkticket.core.domain.performance.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
class Casting(

    @Column(nullable = false, length = 15)
    var name: String,

    @Column(nullable = false, length = 20)
    var role: String,

    @Column(nullable = true, length = 255)
    var imageUrl: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    var schedule: Schedule? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "casting_id")
    var id: Long? = null,
) : BaseEntity()
