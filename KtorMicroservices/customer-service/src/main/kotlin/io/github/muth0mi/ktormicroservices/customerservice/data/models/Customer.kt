package io.github.muth0mi.ktormicroservices.customerservice.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    var id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    var accounts: MutableList<Account> = ArrayList()
}