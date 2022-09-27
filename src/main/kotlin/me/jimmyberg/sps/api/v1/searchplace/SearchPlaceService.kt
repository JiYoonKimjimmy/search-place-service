package me.jimmyberg.sps.api.v1.searchplace

import me.jimmyberg.sps.openapi.OpenApiService
import me.jimmyberg.sps.support.enumerate.OpenApiType
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchPlaceService(
    val openApiService: OpenApiService
) {

    fun searchPlace(keyword: String): SearchPlaceResponse {
        val result = mutableListOf<SearchPlaceModel>()

        val kakaoResponse = openApiService.searchPlace(OpenApiType.KAKAO, keyword)
        val naverResponse = openApiService.searchPlace(OpenApiType.NAVER, keyword)

        val kakaoQueue = LinkedList<SearchPlaceModel>()
        val naverQueue = LinkedList<SearchPlaceModel>()
        val compare = { list: List<SearchPlaceModel>, value: String -> list.find { it.title == value } }

        kakaoResponse.forEach { compare(naverResponse, it.title)?.let { k -> result += k } ?: kakaoQueue.add(it) }
        naverResponse.forEach { compare(kakaoResponse, it.title) ?: kakaoQueue.add(it) }

        while (kakaoQueue.isNotEmpty()) result += kakaoQueue.pop()
        while (naverQueue.isNotEmpty()) result += naverQueue.pop()

        return SearchPlaceResponse(places = result, size = result.size)
    }

}