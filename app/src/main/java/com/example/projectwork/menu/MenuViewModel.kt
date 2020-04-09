package com.example.projectwork.menu

import android.app.Application
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.Repository
import com.example.projectwork.database.PolyglotDatabaseDao
import com.example.projectwork.network.ListOfLanguages
import kotlinx.coroutines.*
//убрал передачу dao через конструктор, чтобы не создавать Factory, потому что это геморой
class MenuViewModel(app : Application) : AndroidViewModel(app) {

//    val repository = (app as App).repository
//    val userName : MutableLiveData<String> = MutableLiveData()
//    val wordsCount : MutableLiveData<Int> = MutableLiveData()
//    val averageMistakes : MutableLiveData<Float> = MutableLiveData()
//    val userPhoto : MutableLiveData<String> = MutableLiveData()

    private val myApp = app as App
    //предпочтительно вот так получать dao
    private val database : PolyglotDatabaseDao = myApp.database.polyglotDatabaseDao
    private val remoteService = myApp.remoteService

    private var okWords = database.getStudiedWords(myApp.currentLanguage)


    val oldWordsButtonVisible = Transformations.map(okWords) {
        it?.isNotEmpty()
    }

    val languages = liveData{ emit(remoteService.getListOfLanguages()) }

    val word = liveData{ emit(remoteService.getWordInfo(1, 1)) }

}