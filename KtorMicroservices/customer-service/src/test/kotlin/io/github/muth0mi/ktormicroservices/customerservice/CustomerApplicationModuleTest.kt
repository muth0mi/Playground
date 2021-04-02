package io.github.muth0mi.ktormicroservices.customerservice

import io.github.muth0mi.ktormicroservices.customerservice.data.models.Customer
import io.github.muth0mi.ktormicroservices.customerservice.data.repository.CustomerRepository
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class CustomerApplicationModuleTest {

    @Test
    fun testGetBlankCustomers() {
        withTestApplication({ main(testing = true) }) {
            handleRequest(HttpMethod.Get, "/customers").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals("""No customers found""", response.content)
            }
        }
    }

    @Test
    fun testAddCustomer() {
        val repository = CustomerRepository()
        repository.addCustomer(Customer(1, "Jane", "Doe", "jdoe@mail.com"))
        withTestApplication({ main(testing = true) }) {
            handleRequest(HttpMethod.Post, "/customers") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(mapOf(
                    "id" to "1",
                    "firstName" to "Jane",
                    "lastName" to "Smith",
                    "email" to "jane.smith@company.com"
                )))
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
                assertEquals(
                    """{"id":1,"firstName":"Jane","lastName":"Smith","email":"jane.smith@company.com","accounts":[]}""",
                    response.content
                )
            }
        }
    }
}