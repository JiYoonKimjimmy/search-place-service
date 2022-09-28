package me.jimmyberg.sps.api.v1.keyword

data class FindKeywordTopRankResponse(
    val ranking: List<KeywordTopRank>
)

data class KeywordTopRank(
    val rank: Int,
    val keyword: String,
    val count: Long
)