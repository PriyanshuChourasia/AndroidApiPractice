package com.priyanshu.androidapipractice.api

import com.priyanshu.androidapipractice.models.LoginRequest
import com.priyanshu.androidapipractice.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {
    @POST("api/v1/auth/authenticate")
    @Headers("Content-Type: application/json")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}