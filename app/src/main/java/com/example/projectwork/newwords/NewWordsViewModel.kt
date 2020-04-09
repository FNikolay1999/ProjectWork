package com.example.projectwork.newwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotDatabaseDao
import com.example.projectwork.network.SingleWord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewWordsViewModel(app : Application) : AndroidViewModel(app) {
    private val myApp = app as App
    //предпочтительно вот так получать dao
    private val database : PolyglotDatabaseDao = myApp.database.polyglotDatabaseDao
    private val remoteService = myApp.remoteService

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private var word = database.getFirstWord(myApp.currentLanguage)
    var intWord: SingleWord? = null

    init {
        getOneWord()
    }

    private fun getOneWord() {
        coroutineScope.launch {
            intWord = myApp.remoteService.getWordInfo(word!!.langId, word!!.wordId)
        }
    }

    fun nextWord() {
        coroutineScope.launch {
            word?.originalWord = intWord?.originWord ?: "unknown"
            word?.isStudied = true
            word?.let { database.update(it) }

            intWord = myApp.remoteService.getWordInfo(word!!.langId, word!!.wordId)
        }
    }

}
