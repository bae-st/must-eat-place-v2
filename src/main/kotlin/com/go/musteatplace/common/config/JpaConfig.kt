package com.go.musteatplace.common.config

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

@Configuration
class JpaConfig {
  @Bean
  fun entityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    dataSource: DataSource?
  ): LocalContainerEntityManagerFactoryBean {
    return builder
      .dataSource(dataSource)
      .packages("com.go.musteatplace")
      .persistenceUnit("entityManagerFactory")
      .build()
  }
}
