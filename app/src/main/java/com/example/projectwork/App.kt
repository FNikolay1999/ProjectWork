package com.example.projectwork

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.projectwork.database.PolyglotDatabase
import com.example.projectwork.settings.LanguageData
import okhttp3.internal.Internal.instance

class App : Application() {

    lateinit var userPreferences: UserPreferences
    lateinit var repository: Repository
    lateinit var database : PolyglotDatabase

    data class Word(var word : String, var transcrypt: String, var translation: String)

    var currentLanguage: Long = 1
    var dictWord = Word("unknown", "unknown", "unknown")
    lateinit var allLanguages: List<LanguageData>

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(applicationContext)
        repository = Repository(userPreferences)
        //добавил database в App
        database = PolyglotDatabase.getInstance(this)
        allLanguages = listOf(LanguageData(1, "lang1", 0), LanguageData(2, "lang2", 0))//запрос
    }
}