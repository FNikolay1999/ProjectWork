package com.example.projectwork

import android.app.Application
import com.example.projectwork.database.PolyglotDatabase

class App : Application() {


    lateinit var userPreferences: UserPreferences
    lateinit var repository: Repository
    lateinit var database : PolyglotDatabase

    var currentLanguage: Long = 1

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(applicationContext)
        repository = Repository(userPreferences)
        //добавил database в App
        database = PolyglotDatabase.getInstance(this)
    }
}