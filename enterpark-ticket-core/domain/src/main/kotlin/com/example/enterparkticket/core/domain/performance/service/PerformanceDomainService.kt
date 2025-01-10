package com.example.enterparkticket.core.domain.performance.service

import com.example.enterparkticket.core.domain.performance.domain.Performance
import com.example.enterparkticket.core.domain.performance.exception.NotFoundPerformanceException
import com.example.enterparkticket.core.domain.performance.repository.PerformanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PerformanceDomainService(
    private val performanceRepository: PerformanceRepository,
) {

    fun findPerformanceById(performanceId: Long): Performance {
        return performanceRepository.findById(performanceId).orElseThrow {
            NotFoundPerformanceException()
        }
    }
}
