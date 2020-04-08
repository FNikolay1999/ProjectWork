package com.example.projectwork.dictionary

import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DictionaryViewModel : ViewModel() {

    var mApp = App()
    private val database : PolyglotDatabaseDao = (mApp).database.polyglotDatabaseDao
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var okWords = database.getStudiedWords(mApp.currentLanguage)



}
