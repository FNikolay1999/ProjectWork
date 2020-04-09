package com.example.projectwork.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.projectwork.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AppSettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val myApp = application as App
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    private val stub : LiveData<List<LanguageData>> = liveData {
//        emit(listOf(LanguageData(1, "lang1"), LanguageData(2, "lang2")))
//    }
//    val languages = stub//Запрос в интернет
}
