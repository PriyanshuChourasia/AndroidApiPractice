package com.priyanshu.androidapipractice.util

import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import com.priyanshu.androidapipractice.ui.Constants

class SessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.UserSession,Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveAuthToken(token: String?){
        editor.putString(Constants.AuthToken,token)
        editor.apply() // async save
    }

    fun getAuthToken(): String?{
        println(Constants.AuthToken)
        return sharedPreferences.getString(Constants.AuthToken,null)
    }

    fun clearSession(){
        editor.clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean{
        return !sharedPreferences.getString(Constants.AuthToken,null).isNullOrEmpty()
    }
}