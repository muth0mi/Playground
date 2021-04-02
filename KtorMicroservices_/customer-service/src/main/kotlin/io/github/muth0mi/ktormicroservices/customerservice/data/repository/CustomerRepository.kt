package io.github.muth0mi.ktormicroservices.customerservice.data.repository

import io.github.muth0mi.ktormicroservices.customerservice.data.models.Customer
import kotlin.random.Random

class CustomerRepository {

    val customers: MutableList<Customer> = arrayListOf()

    fun addCustomer(customer: Customer) {
        customer.id = customer.id ?: Random.nextInt()
        customers.add(customer)
    }

    fun removeCustomer(customer_id: Int): Boolean {
        return customers.removeIf { it.id == customer_id }
    }

}