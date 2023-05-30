package com.kalabekov.frontend.dao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiBuilder {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://silly-poncho-seal.cyclic.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun buildApiClient(): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


}