package com.example.projectwork.oldwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.random.Random
import kotlin.random.nextLong

class OldWordsViewModel(app : Application) : AndroidViewModel(app) {
    private val database : PolyglotDatabaseDao = (app as App).database.polyglotDatabaseDao
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var mApp = app as App
    private var okWords = database.getStudiedWords(mApp.currentLanguage)
    private val random = okWords.map {
        if (it.isEmpty()){
            -1
        }else{
            it.random()
        }
    }

}
