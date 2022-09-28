package me.jimmyberg.sps.api.v1.keyword

import me.jimmyberg.sps.core.entity.KeywordCount
import me.jimmyberg.sps.core.repository.KeywordCountRepository
import org.springframework.stereotype.Service

@Service
class KeywordService(
    val keywordCountRepository: KeywordCountRepository
) {

    /**
     * Top Rank Keyword 조회 요청 처리
     */
    fun findKeywordTopRank(): FindKeywordTopRankResponse =
        keywordCountRepository
            .findTopRankKeywords()
            .mapIndexed { index, keywordCount ->
                KeywordTopRank(
                    rank = index + 1,
                    keyword = keywordCount.keyword,
                    count = keywordCount.count
                )
            }
            .let { FindKeywordTopRankResponse(keywordTopRanks = it) }

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