package com.go.musteatplace.common.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfig {

  @Bean
  fun config(): CircuitBreaker {
    return CircuitBreaker.ofDefaults("searchActual")
  }
}
