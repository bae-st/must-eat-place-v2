package com.go.musteatplace.search.application

import com.go.musteatplace.search.domain.SearchHistory
import com.go.musteatplace.search.presentation.dto.*
import org.springframework.stereotype.Service

@Service
class SearchService(
  private val searchManager: SearchWorker,
  private val searchAppender: SearchAppender
) {
  fun search(request: SearchRequest): List<SearchResultsDto>? {
    val searchResultsJson = searchManager.searchActual(request)

    if (searchResultsJson != null) {
      val searchResults = searchManager.parseSearchResults(searchResultsJson)

      if (searchResults?.isNotEmpty() == true) {
        val firstResult = searchResults[0]
        val searchHistory = SearchHistory(
          keyword = request.keyword,
          name = firstResult.name,
          url = firstResult.url,
          address = firstResult.address,
          road_address = firstResult.roadAddress,
          phone = firstResult.phone,
          category = firstResult.category,
          mapx = firstResult.mapx,
          mapy = firstResult.mapy
        )
        searchAppender.save(searchHistory)
      }
      return searchResults
    } else {
      throw RuntimeException("Fail to get search result")
    }
  }
}
