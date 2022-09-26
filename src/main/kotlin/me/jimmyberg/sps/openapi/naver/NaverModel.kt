package me.jimmyberg.sps.openapi.naver

import me.jimmyberg.sps.openapi.searchplace.SearchPlaceApi
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * NAVER Open API 장소 검색 요청 정보
 */
data class SearchPlaceByNaverRequest(
    val keyword: String,
    val start: Int? = 1,
    val display: Int? = 5
) {
    private fun params() = "?query=${URLEncoder.encode(keyword, StandardCharsets.UTF_8)}&start=$start&display=$display"

    fun process(webClient: WebClient, properties: SearchPlaceApi.Naver) =
        webClient
            .get()
            .uri(URI("${properties.url()}${params()}"))
            .header("X-Naver-Client-id", properties.clientId)
            .header("X-Naver-Client-Secret", properties.clientSecret)
            .retrieve()
            .bodyToFlux(SearchPlaceByNaverResponse::class.java)
}

/**
 * NAVER Open API 장소 검색 응답 정보
 */
data class SearchPlaceByNaverResponse(
    val items: List<Item>? = null
) {
    data class Item(
        val title: String? = null
    )
}