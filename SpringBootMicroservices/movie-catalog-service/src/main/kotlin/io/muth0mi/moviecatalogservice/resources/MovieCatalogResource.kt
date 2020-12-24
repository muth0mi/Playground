package io.muth0mi.moviecatalogservice.resources

import io.muth0mi.moviecatalogservice.models.CatalogItem
import io.muth0mi.moviecatalogservice.models.Movie
import io.muth0mi.moviecatalogservice.models.UserRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/catalog")
class MovieCatalogResource {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @RequestMapping("/{userId}")
    fun getCatalog(@PathVariable userId: String): List<CatalogItem> {
        val catalogItems = arrayListOf<CatalogItem>()

        val ratings =
            restTemplate.getForObject("http://localhost:8083/ratingsData/users/${userId}", UserRating::class.java)

        ratings?.let { ratings ->
            ratings.userRating.forEach { rating ->
                val movie =
                    restTemplate.getForObject("http://localhost:8081/movies/${rating.movieId}", Movie::class.java)
                movie?.let {
                    catalogItems.add(CatalogItem(movie.name, "Test", rating.rating))
                }
            }
        }

        return catalogItems
    }
}