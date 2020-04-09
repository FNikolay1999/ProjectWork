package com.example.projectwork.stats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class StatsViewModel(app : Application) : AndroidViewModel(app){

    private val database : PolyglotDatabaseDao = (app as App).database.polyglotDatabaseDao
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var mApp = app as App
    private var okWords = database.getStudiedWords(mApp.currentLanguage)
    private var allWords = database.getWords(mApp.currentLanguage)
    private val allLangs = mApp.allLanguages

    val studiedWordsAmount: Int = okWords.value?.size ?: 0
    val wordsAmount: Int = allWords.value?.size ?: 0
    val wordsLeft: Int = wordsAmount - studiedWordsAmount
    val studiedWordsAmountString = "Изучено слов: " + studiedWordsAmount.toString()
    val wordsAmountString = "Всего слов: " + wordsAmount.toString()
    val wordsLeftString = "Осталось слов: " + wordsLeft.toString()

    val getCurrentLanguage: String = "Текущий язык: " + allLangs[(mApp.currentLanguage - 1).toInt()].language

}
