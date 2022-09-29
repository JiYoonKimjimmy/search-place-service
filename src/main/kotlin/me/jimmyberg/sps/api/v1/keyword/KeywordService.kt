package me.jimmyberg.sps.api.v1.keyword

import me.jimmyberg.sps.core.entity.KeywordCount
import me.jimmyberg.sps.core.repository.KeywordCountRepository
import org.springframework.stereotype.Service

@Service
class KeywordService(
    val keywordCountRepository: KeywordCountRepository
) {

    /**
     * Keyword Ranking 조회 요청 처리
     */
    fun findKeywordRanking(limit: Long): FindKeywordTopRankResponse =
        keywordCountRepository
            .findKeywordRanking(limit = limit)
            .mapIndexed { index, keywordCount ->
                KeywordRanking(
                    rank = index + 1,
                    keyword = keywordCount.keyword,
                    count = keywordCount.count
                )
            }
            .let { FindKeywordTopRankResponse(ranking = it) }

    /**
     * 검색 Keyword count 변경 처리
     */
    fun updateKeywordCount(keyword: String) =
        keywordCountRepository
            .findByKeyword(keyword)
            .orElse(KeywordCount(keyword = keyword))
            .apply { count++ }
            .let { keywordCountRepository.save(it) }

}