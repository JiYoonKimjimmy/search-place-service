package me.jimmyberg.sps.openapi

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class OpenApiService(
    val webClient: WebClient
) {

    fun searchPlaceByKakao(keyword: String) =
        webClient
            .get()
            .uri(URI("https://dapi.kakao.com/v2/local/search/keyword.json?query=${URLEncoder.encode(keyword, StandardCharsets.UTF_8)}&page=1&size=10"))
            .header("Authorization", "KakaoAK 418ba7355f1b7a4e69347900d110b660")
            .retrieve()
            .bodyToFlux(SearchPlaceByKakaoResponse::class.java)

    fun searchPlaceByNaver(keyword: String): Flux<SearchPlaceByNaverResponse> =
        webClient
            .get()
            .uri(URI("https://openapi.naver.com/v1/search/local.json?query=${URLEncoder.encode(keyword, StandardCharsets.UTF_8)}&start=1&display=10"))
            .header("X-Naver-Client-id", "XI9NYSr_em4IHuvJ_Ovz")
            .header("X-Naver-Client-Secret", "A7Gr0_9sGZ")
            .retrieve()
            .bodyToFlux(SearchPlaceByNaverResponse::class.java)

}