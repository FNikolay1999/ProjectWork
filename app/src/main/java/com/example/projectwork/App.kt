package com.example.projectwork

import android.app.Application

class App : Application() {


    lateinit var userPreferences: UserPreferences
    lateinit var repository: Repository

    var currentLanguage: Long = 1

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(applicationContext)
        repository = Repository(userPreferences)
    }
}