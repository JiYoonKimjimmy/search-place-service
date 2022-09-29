package me.jimmyberg.sps.api.v1.searchplace

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SearchPlaceModelTest {

    @Test
    fun asyncTest() {
        println("start")
        runBlocking {
            val hello = async { task("Hello") }
            val world = async { task("World") }

            println(hello.await())
            println(world.await())
        }
        println("end")
    }

    private suspend fun task(text: String): String {
        delay(5000L)
        return text
    }

    @Test
    fun htmlEscapeTest() {
        println("신한<b>은행</b> 서울시청금융센터".replace("<.*?>".toRegex(), ""))
    }

}