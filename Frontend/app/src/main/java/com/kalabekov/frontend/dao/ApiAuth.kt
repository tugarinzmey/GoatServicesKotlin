package com.kalabekov.frontend.dao

import com.kalabekov.frontend.auth.LoginResponse
import com.kalabekov.frontend.auth.RegisterResponse
import com.kalabekov.frontend.auth.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuth {

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("/auth/registration")
    suspend fun registration(@Body loginRequest: LoginRequest): Response<RegisterResponse>

}