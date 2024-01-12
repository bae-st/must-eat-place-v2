package com.go.musteatplace.search.application

import com.go.musteatplace.search.presentation.dto.SearchRequest
import org.springframework.stereotype.Component

@Component
interface SearchClient {
  fun search(searchParam: SearchRequest): String?
}
