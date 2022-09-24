package me.jimmyberg.sps.openapi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal class OpenApiServiceTest {

    @Test
    fun searchPlaceByKakao() {
        val url = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("dapi.kakao.com")
            .path("/v2/local/search/keyword.json")
            .queryParam("query", URLEncoder.encode("은행", StandardCharsets.UTF_8))
            .queryParam("page", 1)
            .queryParam("size", 10)
            .build()

        println(url.toUriString())
        assertEquals(url.toUriString(), "https://dapi.kakao.com/v2/local/search/keyword.json?query=%EC%9D%80%ED%96%89&page=1&size=10")
    }

}