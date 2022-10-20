package me.jimmyberg.sps.core.service

import me.jimmyberg.sps.openapi.searchplace.SearchPlaceOpenApi
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URI
import java.util.function.Consumer

@Service
class WebClientService(
    val webClient: WebClient,
    val properties: SearchPlaceOpenApi.Kakao
) {

    fun <T> get(uri: URI, responseClass: Class<T>, headers: Consumer<HttpHeaders>): Mono<T> =
        webClient
            .get()
            .uri(uri)
            .headers(headers)
            .retrieve()
            .bodyToMono(responseClass)

}