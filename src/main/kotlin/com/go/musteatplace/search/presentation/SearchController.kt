package com.go.musteatplace.search.presentation

import com.go.musteatplace.search.application.SearchService
import com.go.musteatplace.search.presentation.dto.ApiResponse
import com.go.musteatplace.search.presentation.dto.SearchRequest
import com.go.musteatplace.search.presentation.dto.createApiResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
class SearchController(
  val searchService: SearchService
) {
  @GetMapping("/place")
  fun getSearchInfos(
    @Valid @ModelAttribute searchParam: SearchRequest
  ): ResponseEntity<ApiResponse> {
    val searchResults = searchService.search(searchParam)
    val response = createApiResponse(data = searchResults)
    return ResponseEntity.ok(response)
  }
}
