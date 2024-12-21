package com.example.enterparkticket.core.domain.performance.repository

import com.example.enterparkticket.core.domain.performance.domain.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository : JpaRepository<Performance, Long> {
}
