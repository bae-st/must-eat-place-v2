package com.go.musteatplace.keyword.domain.repository

import com.go.musteatplace.keyword.presentation.dto.KeywordResponse
import com.go.musteatplace.search.domain.QSearchHistory
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import java.time.LocalDateTime

class SearchHistoryRepositoryCustomImpl(
  @Qualifier("mainJpaQueryFactory")
  private val queryFactory: JPAQueryFactory
) : SearchHistoryRepositoryCustom {

  override fun findTop10PopularKeywords(): List<KeywordResponse> {
    val searchHistory = QSearchHistory.searchHistory

    return queryFactory
      .select(
        Projections.constructor(
          KeywordResponse::class.java,
          searchHistory.keyword,
          searchHistory.keyword.count()
        )
      )
      .from(searchHistory)
      .where(searchHistory.createdDateTime.after(LocalDateTime.now().minusDays(3))) // TODO: check TIMEZONE
      .groupBy(searchHistory.keyword)
      .orderBy(searchHistory.keyword.count().desc())
      .limit(10)
      .fetch()
  }
}
