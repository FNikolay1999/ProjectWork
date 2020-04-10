package com.example.projectwork.newwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotData
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


    var intWord: SingleWord? = SingleWord(0, "Wait", "Wait", "Wait", "http://mmcspolyglot.mcdir.ru/images/english_default.jpg")
    private lateinit var word: PolyglotData

    init {
        getOneWord()
    }

    private fun getOneWord() {
        coroutineScope.launch(Dispatchers.IO) {
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
