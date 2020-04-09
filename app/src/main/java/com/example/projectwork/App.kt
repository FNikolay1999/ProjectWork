package com.example.projectwork

import android.app.Application
import com.example.projectwork.database.PolyglotDatabase
import com.example.projectwork.network.PolyglotService
import com.example.projectwork.settings.LanguageData
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class App : Application() {

    lateinit var userPreferences: UserPreferences
    lateinit var repository: Repository
    lateinit var database : PolyglotDatabase
    val remoteService : PolyglotService by lazy {
        PolyglotService()
    }

    data class Word(var word : String, var transcrypt: String, var translation: String)

    var currentLanguage: Long = 1
    var dictWord = Word("unknown", "unknown", "unknown")
    lateinit var allLanguages: List<LanguageData>

//    suspend fun getLangs() {
//        coroutineScope.launch {
//            var langs = remoteService.getListOfLanguages()
//            try {
//                val listResult = langs
//            }
//        }
//    }

    suspend fun getLangsResponse() {
        var listResult = remoteService.getListOfLanguages()
        allLanguages = listOf(LanguageData(1, listResult!!.languages, listResult!!.wordsCount))
    }

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(applicationContext)
        repository = Repository(userPreferences)
        //добавил database в App
        database = PolyglotDatabase.getInstance(this)
        //TODO Запрос на получение списка языков и сравнение с текущей базой
        //allLanguages = listOf(LanguageData(1, "lang1", 0), LanguageData(2, "lang2", 0))


    }
}