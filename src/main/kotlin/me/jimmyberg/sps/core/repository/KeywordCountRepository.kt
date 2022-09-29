package me.jimmyberg.sps.core.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import me.jimmyberg.sps.core.entity.KeywordCount
import me.jimmyberg.sps.core.entity.QKeywordCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface KeywordCountRepository : JpaRepository<KeywordCount, Long>, KeywordCountQRepository {
    fun findByKeyword(keyword: String): Optional<KeywordCount>
}

interface KeywordCountQRepository {
    fun findKeywordRanking(limit: Long): List<KeywordCount>
}

@Repository
class KeywordCountQRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : KeywordCountQRepository {

    override fun findKeywordRanking(limit: Long): List<KeywordCount> =
        QKeywordCount.keywordCount
            .let {
                jpaQueryFactory
                    .selectFrom(it)
                    .orderBy(it.count.desc())
                    .limit(limit)
                    .fetch()
            }

}