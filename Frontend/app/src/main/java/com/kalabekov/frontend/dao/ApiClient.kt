package com.kalabekov.frontend.dao

import com.kalabekov.frontend.models.Client
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {
    @GET("/clients")
    fun getClients(@Header("Authorization") token: String): Call<List<Client>>

    @DELETE("/clients/{id}")
    fun deleteClient(@Header("Authorization") token: String, @Path("id") id: String): Call<Void>

    @PUT("/clients")
    fun updateClient(
        @Header("Authorization") token: String,
        @Body client: Client?
    ): Call<Client>

    @POST("/clients")
    fun addClient(
        @Header("Authorization") token: String,
        @Body client: Client?
    ): Call<Client>


}