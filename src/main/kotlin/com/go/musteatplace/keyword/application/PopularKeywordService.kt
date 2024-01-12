package com.go.musteatplace.keyword.application

import com.go.musteatplace.keyword.presentation.dto.KeywordResponse
import com.go.musteatplace.search.domain.repository.SearchHistoryRepository
import org.springframework.stereotype.Service

@Service
class PopularKeywordService(
  private val repository: SearchHistoryRepository,
) {
  fun getTop10PopularKeywords(): List<KeywordResponse> {
    return repository.findTop10PopularKeywords()
  }
}
