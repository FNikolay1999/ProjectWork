package com.example.projectwork.settings

import android.app.Application
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.network.ListOfLanguages
import kotlinx.coroutines.*

class AppSettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val myApp = application as App
    private var viewModelJob = Job()
    private val remoteService = myApp.remoteService

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val stub : LiveData<List<LanguageData>> = liveData {
        emit(listOf(LanguageData(1, "English", 8050)))
    }
    var listResult : MutableLiveData<ListOfLanguages?> = MutableLiveData(ListOfLanguages(1, "English", 100000))
    var languages = stub//Запрос в интернет

    init {
        startLangs()
    }

    private fun startLangs() {
        coroutineScope.launch(Dispatchers.IO) {
            listResult.postValue(remoteService.getListOfLanguages())
//            languages = liveData {
//                listOf(1, listResult!!.value!!.languages, listResult!!.value!!.count)
//            }
        }
    }

    fun changeLangs() {
        languages = liveData {
            listOf(1, listResult!!.value!!.languages, listResult!!.value!!.count)
        }
    }

}
