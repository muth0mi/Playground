package io.muth0mi.moviecatalogservice.resources

import io.muth0mi.moviecatalogservice.models.CatalogItem
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog")
class MovieCatalogResource {

    @RequestMapping("/{userId}")
    fun getCatalog(@PathVariable userId: String): List<CatalogItem> {
        return listOf(
            CatalogItem("Transformers", "Test", 4)
        )
    }
}