package com.kalabekov.frontend.dao

import com.kalabekov.frontend.service.Service
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/services")
    fun getServices(@Header("Authorization") token: String): Call<List<Service>>

    @DELETE("/services/{id}")
    fun deleteService(@Header("Authorization") token: String, @Path("id") id: String): Call<Void>

    @PUT("/services")
    fun updateService(
        @Header("Authorization") token: String,
        @Body service: Service?
    ): Call<Service>

    @POST("/services")
    fun addService(
        @Header("Authorization") token: String,
        @Body service: Service?
    ): Call<Service>


}