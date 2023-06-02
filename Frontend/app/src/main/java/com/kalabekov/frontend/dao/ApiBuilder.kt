package com.kalabekov.frontend.dao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
    fun buildApiAuth(): ApiAuth {
        return retrofit.create(ApiAuth::class.java)
    }
    fun buildApiService():ApiService{
        return retrofit.create(ApiService::class.java)
    }
    fun buildApiPayment():ApiPayment{
        return retrofit.create(ApiPayment::class.java)
    }


}