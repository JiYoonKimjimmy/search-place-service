package me.jimmyberg.sps.api.v1.searchplace

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/search/place")
@RestController
class SearchPlaceController(
    val searchPlaceService: SearchPlaceService
) {

    @GetMapping
    fun searchPlace(@RequestParam keyword: String) = searchPlaceService.searchPlace(keyword)

}