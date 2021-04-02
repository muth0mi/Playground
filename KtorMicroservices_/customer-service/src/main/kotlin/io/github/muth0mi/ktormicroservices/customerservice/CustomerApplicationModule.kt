package io.github.muth0mi.ktormicroservices.customerservice

import io.github.muth0mi.ktormicroservices.customerservice.data.models.Account
import io.github.muth0mi.ktormicroservices.customerservice.data.models.Customer
import io.github.muth0mi.ktormicroservices.customerservice.data.repository.CustomerRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.slf4j.Logger
import org.slf4j.event.Level


@Suppress("unused") // Referenced in application.conf
fun Application.main(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(CallLogging) {
        level = Level.TRACE
        callIdMdc("X-Request-ID")
    }
    install(CallId) {
        generate(10)
    }

    routing {
        customerRouting(log)
    }
}

fun Route.customerRouting(log: Logger) {
    val repository = CustomerRepository()
    route("/customers") {
        get {
            val customers = repository.customers
            if (customers.isNotEmpty()) call.respond(customers)
            else call.respondText("No customers found", status = HttpStatusCode.NotFound)
        }
        get("/{id}") {
            val id: String = call.parameters["id"] ?: return@get call.respondText(
                "No customer found", status = HttpStatusCode.NotFound
            )
            val accounts = arrayListOf<Account>() // client.get<Accounts>("http://account-service/accounts/customer/$id")
            val customer = repository.customers.last { it.id == id.toInt() }
            customer.accounts.addAll(accounts)
            call.respond(customer)
        }
        post {
            val customer: Customer = call.receive()
            repository.addCustomer(customer)
            log.info("$customer")
            call.respond(message = customer, status=HttpStatusCode.Created)
        }
        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (repository.removeCustomer(id.toInt())) {
                call.respondText("", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Customer Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}