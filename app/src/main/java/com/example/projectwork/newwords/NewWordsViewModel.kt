package com.example.projectwork.newwords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotData
import com.example.projectwork.database.PolyglotDatabaseDao
import com.example.projectwork.network.SingleWord
import kotlinx.coroutines.*

class NewWordsViewModel(app : Application) : AndroidViewModel(app) {
    private val myApp = app as App
    //предпочтительно вот так получать dao
    private val database : PolyglotDatabaseDao = myApp.database.polyglotDatabaseDao
    private val remoteService = myApp.remoteService

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    var intWord: MutableLiveData<SingleWord?> = MutableLiveData(SingleWord(0, "Wait", "Wait", "Wait", "http://mmcspolyglot.mcdir.ru/images/fly.jpg"))
    var word: MutableLiveData<PolyglotData?> = MutableLiveData(null)

    init {
        startingWork()
    }

    suspend fun getOneWord() {
        word.postValue(database.getFirstWord(myApp.currentLanguage))
    }

    suspend fun getOneWordInternet() {
        intWord.postValue(word?.value?.langId?.let { word?.value?.wordId?.let { it1 ->
            myApp.remoteService.getWordInfo(it,
                it1
            )
        } })

    }

    private fun startingWork() {
        coroutineScope.launch(Dispatchers.IO) {
            getOneWord()
            delay(500)
            getOneWordInternet()

        }
    }

    fun nextWord() {
        coroutineScope.launch(Dispatchers.IO) {
            word?.value?.originalWord = intWord?.value?.originWord ?: "unknown"
            word?.value?.isStudied = true
            word?.let { database.update(it.value!!) }

            getOneWord()
            delay(500)
            getOneWordInternet()
//            intWord.postValue(word?.value?.langId?.let { word?.value?.wordId?.let { it1 ->
//                myApp.remoteService.getWordInfo(it,
//                    it1
//                )
//            } })

        }
    }

}
