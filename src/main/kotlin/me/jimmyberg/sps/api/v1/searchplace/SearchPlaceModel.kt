package me.jimmyberg.sps.api.v1.searchplace

import me.jimmyberg.sps.openapi.kakao.SearchPlaceByKakaoResponse
import me.jimmyberg.sps.openapi.naver.SearchPlaceByNaverResponse

data class SearchPlaceModel(
    val places: List<SearchPlace> = listOf(),
    val isEnd: Boolean = true
)

data class SearchPlace(
    val name: String,
    val phone: String,
    val address: String,
    val category: String
) {
    companion object {
        fun of(document: SearchPlaceByKakaoResponse.Document): SearchPlace =
            SearchPlace(
                name = document.placeName.removeHTML(),
                phone = document.phone,
                address = document.addressName,
                category = document.categoryName
            )

        fun of(item: SearchPlaceByNaverResponse.Item): SearchPlace =
            SearchPlace(
                name = item.title.removeHTML(),
                phone = item.telephone,
                address = item.address,
                category = item.category
            )
    }
}

data class SearchPlaceResponse(
    val places: List<SearchPlace>,
    val size: Int
)

fun String.removeHTML() = this.replace("<.*?>".toRegex(), "")