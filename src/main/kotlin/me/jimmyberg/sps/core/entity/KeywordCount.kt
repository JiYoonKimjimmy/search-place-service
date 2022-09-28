package me.jimmyberg.sps.core.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "KEYWORD_COUNT")
class KeywordCount(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val keyword: String,
    var count: Long = 0
)