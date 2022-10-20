package me.jimmyberg.sps.openapi

import me.jimmyberg.sps.api.v1.searchplace.SearchPlaceModel
import me.jimmyberg.sps.core.service.WebClientService
import me.jimmyberg.sps.openapi.kakao.SearchPlaceByKakaoRequest
import me.jimmyberg.sps.openapi.naver.SearchPlaceByNaverRequest
import me.jimmyberg.sps.openapi.searchplace.SearchPlaceOpenApi
import me.jimmyberg.sps.support.enumerate.OpenApiType
import me.jimmyberg.sps.support.enumerate.OpenApiType.KAKAO
import me.jimmyberg.sps.support.enumerate.OpenApiType.NAVER
import org.springframework.stereotype.Service

@Service
class OpenApiService(
    val webClientService: WebClientService,
    val searchPlaceKakaoApi: SearchPlaceOpenApi.Kakao,
    val searchPlaceNaverApi: SearchPlaceOpenApi.Naver
) {

    fun searchPlace(openApiType: OpenApiType, keyword: String, page: Int = 1, size: Int = 5): SearchPlaceModel =
        when (openApiType) {
            KAKAO -> SearchPlaceByKakaoRequest(keyword = keyword, page = page, size = size).process(
                webClientService = webClientService,
                properties = searchPlaceKakaoApi
            )
            NAVER -> SearchPlaceByNaverRequest(keyword = keyword, start = page, display = size).process(
                webClientService = webClientService,
                properties = searchPlaceNaverApi
            )
        } ?: SearchPlaceModel()

}