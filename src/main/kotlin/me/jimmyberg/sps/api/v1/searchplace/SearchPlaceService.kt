package me.jimmyberg.sps.api.v1.searchplace

import me.jimmyberg.sps.openapi.OpenApiService
import me.jimmyberg.sps.support.enumerate.OpenApiType
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchPlaceService(
    val openApiService: OpenApiService
) {

    fun searchPlace(keyword: String): SearchPlaceResponse =
        mutableListOf<SearchPlaceModel>()
            .also { places ->
                val kakaoPlaces = LinkedList(openApiService.searchPlace(OpenApiType.KAKAO, keyword))
                val naverPlaces = LinkedList(openApiService.searchPlace(OpenApiType.NAVER, keyword))

                while (kakaoPlaces.isNotEmpty()) {
                    val kakao = kakaoPlaces.pop()
                    // KAKAO & NAVER 검색 결과 장소명 일치하는 경우, NAVER Queue 항목 제거
                    naverPlaces.find { it.name == kakao.name }?.let { naverPlaces.remove(it) }
                    // KAKAO 검색 결과 먼저 추가
                    places += kakao
                }
                // NAVER Queue 남은 항목 추가
                while (naverPlaces.isNotEmpty()) {
                    places += naverPlaces.pop()
                }
            }.let {
                SearchPlaceResponse(places = it, size = it.size)
            }

}