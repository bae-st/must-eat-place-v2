package com.go.musteatplace.search.application

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.go.musteatplace.search.presentation.dto.*
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.hibernate.service.spi.ServiceException
import org.springframework.stereotype.Component

@Component
class SearchWorker(
  private val naverSearchClient: NaverSearchClient,
  private val kakaoSearchClient: KakaoSearchClient,
  private val searchReader: SearchReader,
  private val objectMapper: ObjectMapper,
) {

  fun searchActual(request: SearchRequest): String? {
    return searchNaver(request = request)
  }

  fun parseSearchResults(res: String): List<SearchResultsDto>? {
    try {
      val naverSearchResponse = objectMapper.readValue<NaverSearchResponse>(res)
      return naverSearchResponse.items.map { NaverSearchResultsAdapter(it) }
    } catch (e: JsonProcessingException) {
      throw ServiceException("Error parsing search results", e)
    }
  }

  @CircuitBreaker(name = "searchNaver", fallbackMethod = "searchKakao")
  fun searchNaver(request: SearchRequest): String? {
    return naverSearchClient.search(request)
  }

  // 수정 필요
  @CircuitBreaker(name = "searchKakao", fallbackMethod = "")
  fun searchKakao(request: SearchRequest, e: Throwable): String? {
    try {
      return kakaoSearchClient.search(request)
    } catch (e: Exception) {
      return searchReader.getByKeyword(keyword = request.keyword)
    }
  }
}
