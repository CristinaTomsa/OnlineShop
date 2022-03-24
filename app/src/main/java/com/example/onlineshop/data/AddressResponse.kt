package com.example.onlineshop.data

import java.io.Serializable

data class AddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)

data class Addresse(
    val address: String,
    val address_id: String,
    val title: String
): Serializable {
    companion object{
        const val KEY_ADDRESS = "address"
    }
}