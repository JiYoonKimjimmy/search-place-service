package me.jimmyberg.sps.api.v1.keyword

data class FindKeywordTopRankResponse(
    val ranking: List<KeywordRanking>
)

data class KeywordRanking(
    val rank: Int,
    val keyword: String,
    val count: Long
)