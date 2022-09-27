package me.jimmyberg.sps.api.v1.searchplace

import org.junit.jupiter.api.Test

class SearchPlaceModelTest {

    @Test
    fun htmlEscapeTest() {
        println("신한<b>은행</b> 서울시청금융센터".replace("<.*?>".toRegex(), ""))
    }

}