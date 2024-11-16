package com.example.enterparkticket.core.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(

    @Column(updatable = false)
    @CreatedDate
    private var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    private var lastModifiedDate: LocalDateTime? = null,

    private var deletedDate: LocalDateTime? = null,
) {

    fun deleteSoftly() {
        deletedDate = LocalDateTime.now()
    }
}