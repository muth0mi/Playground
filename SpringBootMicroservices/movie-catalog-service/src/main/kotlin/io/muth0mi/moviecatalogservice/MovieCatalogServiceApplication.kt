package io.muth0mi.moviecatalogservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class MovieCatalogServiceApplication {

    @Bean
    fun getRestTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<MovieCatalogServiceApplication>(*args)
}
