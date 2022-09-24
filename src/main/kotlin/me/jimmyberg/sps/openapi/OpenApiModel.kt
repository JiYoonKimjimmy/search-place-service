package me.jimmyberg.sps.openapi

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * KAKAO 장소 검색 Open API 응답 정보
 */
data class SearchPlaceByKakaoResponse(
    val documents: List<Document>? = null
) {
    data class Document(
        @field:JsonProperty("place_name")
        val placeName: String? = null
    )
}

/**
 * NAVER 장소 검색 Open API 응답 정보
 */
data class SearchPlaceByNaverResponse(
    val items: List<Item>? = null
) {
    data class Item(
        val title: String? = null
    )
}