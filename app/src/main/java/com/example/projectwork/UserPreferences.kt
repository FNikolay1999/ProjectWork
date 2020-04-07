package com.example.projectwork

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context : Context) {

    companion object{
        private val tokenKey : String = "TOKEN_KEY"
    }
    private val preferences =  context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    var userToken : String = ""
    get() {
        return preferences.getString(tokenKey, "")!!
    }
    set(value) {
        field = value
        preferences.edit().putString(tokenKey, value).apply()
    }

}