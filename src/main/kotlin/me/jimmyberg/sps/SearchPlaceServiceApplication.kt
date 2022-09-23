package me.jimmyberg.sps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SearchPlaceServiceApplication

fun main(args: Array<String>) {
    runApplication<SearchPlaceServiceApplication>(*args)
}
