package com.example.projectwork.oldwordrepeat

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.logging.Logger
import kotlin.jvm.javaClass

class OldWordRepeatViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val myApp = application as App

    val WordString = myApp.dictWord.word
    val TranscryptionString = myApp.dictWord.transcrypt
    val TranslationString = myApp.dictWord.translation

    val wordText = "Word"

    private val dao = myApp.database.polyglotDatabaseDao

    suspend fun getWord(id : Long) : PolyglotData{
        return dao.getWord(id)
    }
//    val WordString: String = myApp.dictWord.word
//    val TranscryptionString: String = mApp.dictWord.transcrypt
//    val translationString: String = mApp.dictWord.translation

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
