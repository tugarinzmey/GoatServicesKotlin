package com.kalabekov.frontend.client

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Client(
    val _id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val status: String
) : Serializable, Parcelable {

    companion object {
        fun createWithoutId(
            firstName: String,
            lastName: String,
            email: String,
            phone: String,
            status: String
        ): Client {
            return Client(null, firstName, lastName, email, phone, status)
        }

    }

    override fun toString(): String {
        return firstName
    }
}