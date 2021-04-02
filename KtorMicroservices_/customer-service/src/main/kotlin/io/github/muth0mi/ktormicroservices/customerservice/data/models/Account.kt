package io.github.muth0mi.ktormicroservices.customerservice.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(val id: Int, val balance: Int, val number: String) {
    var customerId: Int = 0
}