package com.go.musteatplace.search.presentation.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class SearchRequest(
  @field: NotNull(message = "keyword는 필수 입력값입니다.")
  val keyword: String,

  @field:Pattern(regexp = "random|comment", message = "sort는 'random' 또는 'comment'만 가능합니다.")
  val sort: String = "random"
)
