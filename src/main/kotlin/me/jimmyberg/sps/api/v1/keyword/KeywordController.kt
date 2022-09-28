package me.jimmyberg.sps.api.v1.keyword

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/keyword")
@RestController
class KeywordController(
    val keywordService: KeywordService
) {

    @GetMapping("/top-rank")
    fun findKeywordTopRank() = keywordService.findKeywordTopRank()

}