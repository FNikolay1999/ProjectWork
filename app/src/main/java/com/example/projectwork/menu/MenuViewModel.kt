package com.example.projectwork.menu

import android.app.Application
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.Repository
import com.example.projectwork.database.PolyglotData
import com.example.projectwork.database.PolyglotDatabaseDao
import com.example.projectwork.databinding.OldWordsFragmentBinding
import com.example.projectwork.network.ListOfLanguages
import com.example.projectwork.settings.LanguageData
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

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private lateinit var hereLanguage: LanguageData
    private lateinit var okWords: LiveData<List<PolyglotData>>
    private lateinit var notOkWords: LiveData<List<PolyglotData>>
    var oldWordsButtonVisible = MutableLiveData<Boolean?>(true)
    var newWordsButtonVisible = MutableLiveData<Boolean?>(true)


    init {
        startLangs()
    }

    suspend fun inserts(begin: Long, finish: Long) {
        for (i in begin..finish) {
            database.insert(PolyglotData(0, myApp.currentLanguage, i, "not_studied_yet", false))
        }
    }

    private fun startLangs() {

        coroutineScope.launch(Dispatchers.IO) {
            val listResult = remoteService.getListOfLanguages()

//            if (listResult != null) {
//                myApp.allLanguages = listOf(LanguageData(1, listResult.languages, listResult.wordsCount))
//            }

            if (listResult!!.wordsCount > database.countWords(myApp.currentLanguage)) {
                val begin = (database.countWords(myApp.currentLanguage) + 1).toLong()
                val finish = listResult.wordsCount
                inserts(begin, finish)
            }

            okWords = database.getStudiedWords(myApp.currentLanguage)
            notOkWords = database.getNotStudiedWords(myApp.currentLanguage)

            val old = Transformations.map(okWords) {
                it?.isNotEmpty()
            }
            oldWordsButtonVisible.value = old.value

            val new = Transformations.map(notOkWords) {
                it?.isNotEmpty()
            }
            newWordsButtonVisible.value = new.value
        }
    }


    val languages = liveData{ emit(remoteService.getListOfLanguages()) }

    val word = liveData{ emit(remoteService.getWordInfo(1, 1)) }

}