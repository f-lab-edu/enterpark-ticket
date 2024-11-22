package com.example.enterparkticket.core.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(

    @Column(updatable = false)
    @CreatedBy
    private var createdBy: String? = null,

    @LastModifiedBy
    private var lastModifiedBy: String? = null,
) : BaseTimeEntity()