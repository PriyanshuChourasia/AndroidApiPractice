package com.priyanshu.androidapipractice.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.priyanshu.androidapipractice.ui.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    @Volatile
    private var retrofit: Retrofit? = null

//    Logging interceptor or debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

//    okHttpClient with Logging
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private fun getInstance(): Retrofit{

        val gson:Gson =  GsonBuilder().create()
        return retrofit ?: synchronized(this){
            retrofit ?: Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().also {
                    retrofit = it
                }
        }
    }
    fun getApi():Api{
        return getInstance().create(Api::class.java)
    }
}

