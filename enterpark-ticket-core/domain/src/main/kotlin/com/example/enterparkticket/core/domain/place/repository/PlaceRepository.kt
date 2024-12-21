package com.example.enterparkticket.core.domain.place.repository

import com.example.enterparkticket.core.domain.place.domain.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long> {
}
