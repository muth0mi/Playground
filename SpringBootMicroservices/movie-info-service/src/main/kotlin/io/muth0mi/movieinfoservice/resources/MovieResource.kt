package io.muth0mi.movieinfoservice.resources

import io.muth0mi.movieinfoservice.models.Movie
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieResource {

    @RequestMapping("/{movieId}")
    fun getMovieInfo(@PathVariable movieId: String): Movie{
        return Movie(movieId, "Test Name")
    }
}