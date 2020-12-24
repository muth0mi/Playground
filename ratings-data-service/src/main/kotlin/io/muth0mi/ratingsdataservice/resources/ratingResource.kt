package io.muth0mi.ratingsdataservice.resources

import io.muth0mi.ratingsdataservice.models.Rating
import io.muth0mi.ratingsdataservice.models.UserRating
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ratingsData")
class ratingResource {

    @RequestMapping("/{movieId}")
    fun getRating(@PathVariable movieId: String): Rating {
        return Rating(movieId, 4)
    }

    @RequestMapping("/users/{userId}")
    fun getUserRating(@PathVariable userId: String): UserRating {
        return UserRating(
            listOf(
                Rating("1234", 4),
                Rating("5678", 3)
            )
        )
    }
}