package me.jimmyberg.sps.openapi.searchplace

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

class SearchPlaceApi {

    @ConfigurationProperties(prefix = "open-api.kakao.search.place")
    @Configuration
    class Kakao {
        lateinit var host: String
        lateinit var path: String
        lateinit var format: String
        lateinit var authorizationKey: String

        fun url(): String = "${host}${path}".replace("{format}", format)
    }

    @ConfigurationProperties(prefix = "open-api.naver.search.place")
    @Configuration
    class Naver {
        lateinit var host: String
        lateinit var path: String
        lateinit var format: String
        lateinit var clientId: String
        lateinit var clientSecret: String

        fun url(): String = "${host}${path}".replace("{format}", format)
    }

}