package com.go.musteatplace.search.application

import com.go.musteatplace.search.presentation.dto.*
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

interface KakaoSearchClient : SearchClient {
  @Component
  class KakaoSearchClientImpl : KakaoSearchClient {
    override fun search(searchParam: SearchRequest): String? {
      val kakaoApiKey = System.getenv("KAKAO_REST_API_KEY")

      val uri = UriComponentsBuilder
        .fromUriString("https://dapi.kakao.com")
        .path("/v2/local/search/keyword.json")
        .queryParam("query", searchParam.keyword)
        .encode()
        .build()
        .toUri()

      val restTemplate = RestTemplate()

      val req = RequestEntity
        .get(uri)
        .header("Authorization", "KakaoAK $kakaoApiKey")
        .build()

      val res = restTemplate.exchange(req, String::class.java) // type 확인

      return res.body
    }
  }
}
