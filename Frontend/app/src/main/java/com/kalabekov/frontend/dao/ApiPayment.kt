package com.kalabekov.frontend.dao

import com.kalabekov.frontend.payment.Payment
import retrofit2.Call
import retrofit2.http.*

interface ApiPayment {
    @GET("/payments")
    fun getPayments(@Header("Authorization") token: String): Call<List<Payment>>

    @DELETE("/payments/{id}")
    fun deletePayment(@Header("Authorization") token: String, @Path("id") id: String): Call<Void>

    @PUT("/payments")
    fun updatePayment(
        @Header("Authorization") token: String,
        @Body payment: Payment?
    ): Call<Payment>

    @POST("/payments")
    fun addPayment(
        @Header("Authorization") token: String,
        @Body payment: Payment?
    ): Call<Payment>

}