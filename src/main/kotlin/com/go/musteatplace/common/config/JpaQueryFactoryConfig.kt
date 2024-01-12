package com.go.musteatplace.common.config

import com.querydsl.jpa.JPQLTemplates
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JpaQueryFactoryConfig {
  @PersistenceContext(unitName = "entityManagerFactory")
  private lateinit var mainEntityManager: EntityManager

  @Bean(name = ["mainJpaQueryFactory"])
  fun jpaQueryFactory() =
    JPAQueryFactory(JPQLTemplates.DEFAULT, mainEntityManager)
}
