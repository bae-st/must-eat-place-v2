package com.go.musteatplace.search.domain.common

import jakarta.persistence.Column
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
class BaseEntity {
  @CreatedDate
  @Column(name = "created_at", insertable = false, updatable = false)
  lateinit var createdDateTime: LocalDateTime

  @LastModifiedDate
  @Column(name = "updated_at", insertable = false, updatable = false)
  lateinit var updatedDateTime: LocalDateTime
}
