package io.muth0mi.moviecatalogservice.resources

import io.muth0mi.moviecatalogservice.models.CatalogItem
import io.muth0mi.moviecatalogservice.models.Movie
import io.muth0mi.moviecatalogservice.models.Rating
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/catalog")
class MovieCatalogResource {

    @RequestMapping("/{userId}")
    fun getCatalog(@PathVariable userId: String): List<CatalogItem> {

        val restTemplate = RestTemplate()

        val ratings: List<Rating> = listOf(
            Rating("1234", 4),
            Rating("5678", 3)
        )

        val catalogItems = arrayListOf<CatalogItem>()
        ratings.forEach { rating ->
            restTemplate.getForObject("http://localhost:8081/movies/${rating.movieId}", Movie::class.java)?.let { movie ->
                catalogItems.add(CatalogItem(movie.name, "Test", rating.rating))
            }
        }

        return catalogItems

        return listOf(
            CatalogItem("Transformers", "Test", 4)
        )
    }
}