package com.example.enterparkticket.core.domain.seat.repository

import com.example.enterparkticket.core.domain.seat.domain.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long> {
}
