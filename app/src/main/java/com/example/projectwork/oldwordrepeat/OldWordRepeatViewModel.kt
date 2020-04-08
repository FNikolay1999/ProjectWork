package com.example.projectwork.oldwordrepeat

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.projectwork.App
import com.example.projectwork.database.PolyglotData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.logging.Logger
import kotlin.jvm.javaClass

class OldWordRepeatViewModel(application: Application) : AndroidViewModel(application) {

    private val myApp = application as App

    val wordText = "Word"

    private val dao = myApp.database.polyglotDatabaseDao

    suspend fun getWord(id : Long) : PolyglotData{
        return dao.getWord(id)
    }
}
