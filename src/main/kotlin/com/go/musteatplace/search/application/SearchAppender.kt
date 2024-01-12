package com.go.musteatplace.search.application

import com.go.musteatplace.search.domain.SearchHistory
import com.go.musteatplace.search.domain.repository.SearchHistoryRepository
import org.springframework.stereotype.Component

@Component
class SearchAppender(
  private val searchHistoryRepository: SearchHistoryRepository,
) {
  fun save(result: SearchHistory) {
    searchHistoryRepository.save(result = result)
  }
}
