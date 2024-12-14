package com.example.enterparkticket.core.domain.seat.domain

import com.example.enterparkticket.core.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
class Place(

    @Column(nullable = false, length = 255)
    var name: String,

    @Column(nullable = false, length = 255)
    var address: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    var id: Long? = null,
) : BaseEntity()
