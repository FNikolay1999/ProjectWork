package com.example.projectwork.oldwordrepeat

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotData
import com.example.projectwork.network.SingleWord
import kotlinx.coroutines.*
import java.util.logging.Logger
import kotlin.jvm.javaClass

class OldWordRepeatViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val myApp = application as App

    val WordString = myApp.dictWord.word
    val TranscryptionString = myApp.dictWord.transcrypt
    val TranslationString = myApp.dictWord.translation

    val wordText = "Word"

    private val dao = myApp.database.polyglotDatabaseDao

    suspend fun getWord(id : Long) : PolyglotData{
        return dao.getWord(id)
    }

    var intWord: MutableLiveData<SingleWord?> = MutableLiveData(SingleWord(0, "Wait", "Wait", "Wait", "http://mmcspolyglot.mcdir.ru/images/default_picture.jpg"))
    var word: MutableLiveData<PolyglotData?> = MutableLiveData(PolyglotData(0, 1, 1, "wait", false))

    init {
        startingWork()
    }

    suspend fun getOneWord() {
        word.postValue(dao.getFirstWord(myApp.currentLanguage))
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
            word.postValue(getWord(1))
            delay(500)
            getOneWordInternet()
        }
    }

    fun splittingWord() = buildString {
        for (line in intWord.value?.translation?.split(';')!!)
            append(line + "\n")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
