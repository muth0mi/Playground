package io.muth0mi.movieinfoservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class MovieInfoServiceApplication

fun main(args: Array<String>) {
    runApplication<MovieInfoServiceApplication>(*args)
}
