package com.example.posyanduhb

import android.content.Context
import android.content.SharedPreferences

class UserPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUserProfile(
        username: String,
        email: String,
        dob: String,
        phone: String
    ) {
        sharedPreferences.edit().apply {
            putString(KEY_USERNAME, username)
            putString(KEY_EMAIL, email)
            putString(KEY_DOB, dob)
            putString(KEY_PHONE, phone)
            apply()
        }
    }

    fun getUsername(): String = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    fun getEmail(): String = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    fun getDob(): String = sharedPreferences.getString(KEY_DOB, "") ?: ""
    fun getPhone(): String = sharedPreferences.getString(KEY_PHONE, "") ?: ""

    companion object {
        private const val PREF_NAME = "user_preferences"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_DOB = "dob"
        private const val KEY_PHONE = "phone"

        @Volatile
        private var instance: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences {
            return instance ?: synchronized(this) {
                instance ?: UserPreferences(context).also { instance = it }
            }
        }
    }
}