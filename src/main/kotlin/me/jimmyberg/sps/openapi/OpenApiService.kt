package me.jimmyberg.sps.openapi

import me.jimmyberg.sps.api.v1.searchplace.SearchPlaceModel
import me.jimmyberg.sps.openapi.kakao.SearchPlaceByKakaoRequest
import me.jimmyberg.sps.openapi.naver.SearchPlaceByNaverRequest
import me.jimmyberg.sps.openapi.searchplace.SearchPlaceOpenApi
import me.jimmyberg.sps.support.enumerate.OpenApiType
import me.jimmyberg.sps.support.enumerate.OpenApiType.KAKAO
import me.jimmyberg.sps.support.enumerate.OpenApiType.NAVER
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class OpenApiService(
    val webClient: WebClient,
    val searchPlaceKakaoApi: SearchPlaceOpenApi.Kakao,
    val searchPlaceNaverApi: SearchPlaceOpenApi.Naver
) {

    fun searchPlace(openApiType: OpenApiType, keyword: String): List<SearchPlaceModel> =
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