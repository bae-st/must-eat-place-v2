package com.go.musteatplace.search.presentation.dto

data class ApiResponse(
  val status: Int,
  val message: String,
  val data: Any?
)

fun <T> createApiResponse(status: Int = 200, message: String = "", data: T? = null): ApiResponse {
  return ApiResponse(status, message, data)
}
