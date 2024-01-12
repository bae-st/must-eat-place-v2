package com.go.musteatplace.search.presentation.dto

interface SearchResultsDto {
  val name: String
  val url: String
  val address: String
  val roadAddress: String
  val phone: String
  val category: String
  val mapx: String
  val mapy: String
}

class NaverSearchResultsAdapter(private val naverDto: NaverSearchResultsDto) : SearchResultsDto {
  override val name: String get() = naverDto.title
  override val url: String get() = naverDto.link
  override val address: String get() = naverDto.address
  override val roadAddress: String get() = naverDto.roadAddress
  override val phone: String get() = naverDto.telephone
  override val category: String get() = naverDto.category
  override val mapx: String get() = naverDto.mapx
  override val mapy: String get() = naverDto.mapy
}

class KakaoSearchResultsAdapter(private val kakaoDto: KakaoSearchResultsDto) : SearchResultsDto {
  override val name: String get() = kakaoDto.place_name
  override val url: String get() = kakaoDto.place_url
  override val address: String get() = kakaoDto.address_name
  override val roadAddress: String get() = kakaoDto.road_address_name
  override val phone: String get() = kakaoDto.phone
  override val category: String get() = kakaoDto.category_name
  override val mapx: String get() = kakaoDto.x
  override val mapy: String get() = kakaoDto.y
}

data class NaverSearchResponse(
  val lastBuildDate: String,
  val total: Int,
  val start: Int,
  val display: Int,
  val items: List<NaverSearchResultsDto>
)

data class KakaoSearchResponse(
  val documents: List<KakaoSearchResultsDto>
)

data class NaverSearchResultsDto(
  val title: String,
  val link: String,
  val category: String,
  val description: String,
  val telephone: String,
  val address: String,
  val roadAddress: String,
  val mapx: String,
  val mapy: String,
)

data class KakaoSearchResultsDto(
  val address_name: String,
  val category_group_code: String,
  val category_group_name: String,
  val category_name: String,
  val distance: String,
  val id: String,
  val phone: String,
  val place_name: String,
  val place_url: String,
  val road_address_name: String,
  val x: String,
  val y: String,
)

data class SearchResponse(
  val keyword: String,
  val searchResults: List<SearchResultsDto>
)
