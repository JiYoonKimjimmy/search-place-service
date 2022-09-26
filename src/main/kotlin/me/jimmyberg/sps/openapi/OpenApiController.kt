package me.jimmyberg.sps.openapi

import me.jimmyberg.sps.support.enumerate.OpenApiType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/open-api/search/place")
@RestController
class OpenApiController(
    val openApiService: OpenApiService
) {

    @GetMapping("/{openApiType}")
    fun searchPlace(
        @PathVariable("openApiType") openApiType: OpenApiType,
        @RequestParam keyword: String
    ) = openApiService.searchPlace(openApiType = openApiType, keyword = keyword)

}