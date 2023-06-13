package com.example.funne.data.model

import android.content.Context
import android.content.SharedPreferences

class FunneSession(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun saveUser(loginResponse: LoginResponse) {
        preferences.edit().apply {
            putString(NAME, loginResponse.name)
            putString(EMAIL, loginResponse.email)
            putString(PICTURE, loginResponse.picture)
            putString(TOKEN, loginResponse.token)
            putBoolean(LOGIN, loginResponse.isLogin)
            apply()
        }
    }

    fun isLoggedIn(): Boolean = preferences.getBoolean(LOGIN, false)

    fun getToken(): String? = preferences.getString(TOKEN, null)

    companion object {
        private const val PREFS = "funne_sessions"
        private const val NAME = "name"
        private const val TOKEN = "token"
        private const val LOGIN = "login"
        private const val EMAIL = "email"
        private const val PICTURE = "picture"
    }
}
