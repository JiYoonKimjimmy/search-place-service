package me.jimmyberg.sps.openapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/open-api/search/place")
@RestController
class OpenApiController(
    val openApiService: OpenApiService
) {

    @GetMapping("/kakao")
    fun searchPlaceByKakao(@RequestParam keyword: String) = openApiService.searchPlaceByKakao(keyword)

    @GetMapping("/naver")
    fun searchPlaceByNaver(@RequestParam keyword: String) = openApiService.searchPlaceByNaver(keyword)

}