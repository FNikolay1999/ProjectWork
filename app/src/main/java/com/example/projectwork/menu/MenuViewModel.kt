package com.example.projectwork.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.projectwork.App
import com.example.projectwork.Repository
import com.example.projectwork.database.PolyglotDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MenuViewModel(
    val database: PolyglotDatabaseDao,
    app : Application) : AndroidViewModel(app) {

//    val repository = (app as App).repository
//    val userName : MutableLiveData<String> = MutableLiveData()
//    val wordsCount : MutableLiveData<Int> = MutableLiveData()
//    val averageMistakes : MutableLiveData<Float> = MutableLiveData()
//    val userPhoto : MutableLiveData<String> = MutableLiveData()

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var okWords = database.getStudiedWords(1)

    val oldWordsButtonVisible = Transformations.map(okWords) {
        it?.isNotEmpty()
    }


}
