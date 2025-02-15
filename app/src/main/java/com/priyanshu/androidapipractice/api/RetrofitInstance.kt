package com.priyanshu.androidapipractice.api

import com.priyanshu.androidapipractice.ui.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    @Volatile
    private var retrofit: Retrofit? = null

    private fun getInstance(): Retrofit{
        return retrofit ?: synchronized(this){
            retrofit ?: Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().also {
                    retrofit = it
                }
        }
    }
    fun getApi():Api{
        return getInstance().create(Api::class.java)
    }
}

