package com.go.musteatplace.keyword.domain.repository

import com.go.musteatplace.keyword.presentation.dto.KeywordResponse

interface SearchHistoryRepositoryCustom {
  fun findTop10PopularKeywords(): List<KeywordResponse>
}
