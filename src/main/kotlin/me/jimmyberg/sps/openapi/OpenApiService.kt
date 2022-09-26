package me.jimmyberg.sps.openapi

import me.jimmyberg.sps.openapi.kakao.SearchPlaceByKakaoRequest
import me.jimmyberg.sps.openapi.naver.SearchPlaceByNaverRequest
import me.jimmyberg.sps.openapi.searchplace.SearchPlaceApi
import me.jimmyberg.sps.support.enumerate.OpenApiType
import me.jimmyberg.sps.support.enumerate.OpenApiType.KAKAO
import me.jimmyberg.sps.support.enumerate.OpenApiType.NAVER
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Service
class OpenApiService(
    val webClient: WebClient,
    val searchPlaceKakaoApi: SearchPlaceApi.Kakao,
    val searchPlaceNaverApi: SearchPlaceApi.Naver
) {

    fun searchPlace(openApiType: OpenApiType, keyword: String): Flux<*> =
        when (openApiType) {
            KAKAO -> SearchPlaceByKakaoRequest(keyword = keyword).process(
                webClient = webClient,
                properties = searchPlaceKakaoApi
            )
            NAVER -> SearchPlaceByNaverRequest(keyword = keyword).process(
                webClient = webClient,
                properties = searchPlaceNaverApi
            )
        }

}