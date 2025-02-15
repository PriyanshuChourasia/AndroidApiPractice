package com.priyanshu.androidapipractice.models

import com.google.gson.annotations.SerializedName

data class AuthToken(
   @SerializedName("access_token") val accessToken: String,
   @SerializedName("refresh_token") val refreshToken: String
)