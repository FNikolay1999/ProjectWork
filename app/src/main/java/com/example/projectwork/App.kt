package com.example.projectwork

import android.app.Application
import android.content.Context
import com.example.projectwork.database.PolyglotDatabase
import okhttp3.internal.Internal.instance

class App : Application() {

    lateinit var userPreferences: UserPreferences
    lateinit var repository: Repository
    lateinit var database : PolyglotDatabase

    data class Word(var word : String, var transcrypt: String, var translation: String)
    /*class Word constructor(val _word: String, val _trancrypt: String, val _translation: String) {
        var word: String
        var transcrypt: String
        var translation: String

        init {
            word = _word
            transcrypt = _trancrypt
            translation = _translation
        }
    }*/

    var currentLanguage: Long = 1
    var dictWord = Word("unknown", "unknown", "unknown")

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(applicationContext)
        repository = Repository(userPreferences)
        //добавил database в App
        database = PolyglotDatabase.getInstance(this)
    }
}