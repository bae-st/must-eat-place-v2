package com.go.musteatplace.search.application

import com.go.musteatplace.search.presentation.dto.*
import org.springframework.context.annotation.Primary
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

interface NaverSearchClient : SearchClient {
  @Component @Primary
  class NaverSearchClientImpl : NaverSearchClient {
    override fun search(searchParam: SearchRequest): String? {
      val naverOpenApiId = System.getenv("NAVER_CLIENT_ID")
      val naverOpenApiSecret = System.getenv("NAVER_CLIENT_SECRET")

      val uri = UriComponentsBuilder
        .fromUriString("https://openapi.naver.com")
        .path("/v1/search/local.json")
        .queryParam("query", searchParam.keyword)
        .queryParam("display", 5)
        .queryParam("start", 1)
        .queryParam("sort", "random")
        .encode()
        .build()
        .toUri()

      val restTemplate = RestTemplate()

      val req = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id", naverOpenApiId)
        .header("X-Naver-Client-Secret", naverOpenApiSecret)
        .build()

      val res = restTemplate.exchange(req, String::class.java)
      return res.body
    }
  }
}
