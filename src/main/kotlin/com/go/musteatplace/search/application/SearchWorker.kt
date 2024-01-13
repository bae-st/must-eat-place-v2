package com.go.musteatplace.search.application

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.go.musteatplace.search.presentation.dto.*
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.hibernate.service.spi.ServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SearchWorker(
  private val naverSearchClient: NaverSearchClient,
  private val kakaoSearchClient: KakaoSearchClient,
  private val searchReader: SearchReader,
  private val objectMapper: ObjectMapper,
) {
  val logger: Logger = LoggerFactory.getLogger(SearchWorker::class.java)

  fun searchActual(request: SearchRequest): String? {
    return searchNaver(request = request)
  }

  fun parseSearchResults(res: String): List<SearchResultsDto>? {
    try {
      val naverSearchResponse = objectMapper.readValue<NaverSearchResponse>(res)
      return naverSearchResponse.items.map { NaverSearchResultsAdapter(it) }
    } catch (e: JsonProcessingException) {
      logger.error("parseSearchResults: ${e.message}")
      throw ServiceException("Error parsing search results", e)
    }
  }

  @CircuitBreaker(name = "searchNaver", fallbackMethod = "searchKakao")
  fun searchNaver(request: SearchRequest): String? {
      return naverSearchClient.search(request)
  }

  @CircuitBreaker(name = "searchKakao", fallbackMethod = "fallbackSearch")
  fun searchKakao(request: SearchRequest): String? {
      logger.warn("Circuit opened")
      return kakaoSearchClient.search(request)
  }

  // TODO: select data from search_history table
  @CircuitBreaker(name = "fallbackSearch")
  fun fallbackSearch(request: SearchRequest, e: Throwable): String? {
    logger.warn("fallbackSearch called")
    return "An error occurred: ${e.message}"
  }
}
