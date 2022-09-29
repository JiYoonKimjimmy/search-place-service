package me.jimmyberg.sps.api.v1.searchplace

import me.jimmyberg.sps.api.v1.keyword.KeywordService
import me.jimmyberg.sps.openapi.OpenApiService
import me.jimmyberg.sps.support.enumerate.OpenApiType.KAKAO
import me.jimmyberg.sps.support.enumerate.OpenApiType.NAVER
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchPlaceService(
    val openApiService: OpenApiService,
    val keywordService: KeywordService
) {

    /**
     * 장소 검색 요청 처리
     */
    fun searchPlace(keyword: String): SearchPlaceResponse =
        searchPlaceToOpenApi(keyword)
            .let { SearchPlaceResponse(places = it, size = it.size) }
            .also { keywordService.updateKeywordCount(keyword) }

    /**
     * 장소 검색 요청 to Open-API
     */
    private fun searchPlaceToOpenApi(keyword: String, page: Int = 1): List<SearchPlace> {
        val places = mutableSetOf<SearchPlace>()

        val kakaoPlaces = openApiService.searchPlace(KAKAO, keyword = keyword, page = page)
        val naverPlaces = openApiService.searchPlace(NAVER, keyword = keyword, page = page)

        val kakaoQueue = LinkedList(kakaoPlaces.places)
        val naverQueue = LinkedList(naverPlaces.places)

        while (kakaoQueue.isNotEmpty()) {
            val kakao = kakaoQueue.pop()
            // KAKAO & NAVER 검색 결과 장소명 일치하는 경우, NAVER Queue 항목 제거
            naverQueue.find { it.name == kakao.name }?.let { naverQueue.remove(it) }
            // KAKAO 검색 결과 먼저 추가
            places += kakao
        }
        // NAVER Queue 남은 항목 추가
        while (naverQueue.isNotEmpty()) {
            places += naverQueue.pop()
        }

        if (places.size <= 5 && !kakaoPlaces.isEnd) {
            // 총 검색 결과가 5 건 이하 & KAKAO 검색 결과 end 아닌 경우
            places += searchPlaceToOpenApi(keyword, page = page + 1)
        }

        return places.toList()
    }

}

