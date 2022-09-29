package me.jimmyberg.sps.openapi.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import me.jimmyberg.sps.api.v1.searchplace.SearchPlace
import me.jimmyberg.sps.api.v1.searchplace.SearchPlaceModel
import me.jimmyberg.sps.openapi.searchplace.SearchPlaceOpenApi
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * KAKAO Open API 장소 검색 요청 정보
 */
data class SearchPlaceByKakaoRequest(
    val keyword: String,
    val page: Int,
    val size: Int
) {

    private fun params() = "?query=${URLEncoder.encode(keyword, StandardCharsets.UTF_8)}&page=$page&size=$size"

    fun process(webClient: WebClient, properties: SearchPlaceOpenApi.Kakao) =
        webClient
            .get()
            .uri(URI("${properties.url()}${params()}"))
            .header("Authorization", "KakaoAK ${properties.authorizationKey}")
            .retrieve()
            .bodyToMono(SearchPlaceByKakaoResponse::class.java)
            .block()!!
            .let { SearchPlaceModel(places = it.documents.map(SearchPlace::of), isEnd = it.meta.isEnd) }

}

/**
 * KAKAO Open API 장소 검색 응답 정보
 */
data class SearchPlaceByKakaoResponse(
    val meta: Meta,
    val documents: List<Document> = listOf()
) {
    data class Meta(
        @field:JsonProperty("is_end")
        val isEnd: Boolean,
        @field:JsonProperty("pageable_count")
        val pageableCount: Int,
        @field:JsonProperty("total_count")
        val totalCount: Int
    )
    data class Document(
        @field:JsonProperty("place_name")
        val placeName: String,
        val phone: String,
        @field:JsonProperty("road_address_name")
        val addressName: String,
        @field:JsonProperty("category_name")
        val categoryName: String
    )
}