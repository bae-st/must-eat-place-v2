package com.go.musteatplace.search.domain

import com.go.musteatplace.search.domain.common.BaseEntity
import com.go.musteatplace.search.presentation.dto.SearchResponse
import jakarta.persistence.*

@Entity
@Table(name = "search_history")
class SearchHistory(
  val keyword: String,
  val name: String,
  val url: String,
  val category: String,
  val address: String,
  val road_address: String,
  val phone: String,
  val mapx: String,
  val mapy: String
) : BaseEntity() {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null

  companion object {
    fun getSearchItems(target: SearchResponse): SearchHistory {
      return SearchHistory(
        keyword = target.keyword,
        name = target.searchResults[0].name,
        category = target.searchResults[0].category,
        url = target.searchResults[0].url,
        phone = target.searchResults[0].phone,
        address = target.searchResults[0].address,
        road_address = target.searchResults[0].roadAddress,
        mapx = target.searchResults[0].mapx,
        mapy = target.searchResults[0].mapy,
      )
    }
  }
}
