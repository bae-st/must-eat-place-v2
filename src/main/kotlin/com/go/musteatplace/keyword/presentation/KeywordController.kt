package com.go.musteatplace.keyword.presentation

import com.go.musteatplace.keyword.application.PopularKeywordService
import com.go.musteatplace.search.presentation.dto.ApiResponse
import com.go.musteatplace.search.presentation.dto.createApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/keyword")
class KeywordController(
  val popularKeywordService: PopularKeywordService
) {
  @GetMapping("/rank")
  fun getKeywordRanks(): ResponseEntity<ApiResponse> {
    val keywordRanks = popularKeywordService.getTop10PopularKeywords()
    val response = createApiResponse(data = keywordRanks)
    return ResponseEntity.ok(response)
  }
}
