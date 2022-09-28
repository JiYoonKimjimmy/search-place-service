package me.jimmyberg.sps.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class ApplicationConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    @Bean
    fun webClient() = WebClient.create()

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(this.entityManager)

}