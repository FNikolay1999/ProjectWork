package com.example.projectwork.oldwordrepeat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectwork.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.logging.Logger
import kotlin.jvm.javaClass

class OldWordRepeatViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var myApp = App()

    val WordString = myApp.dictWord.word

    val wordText = "Word"

    fun getWord(): String {
//        val Log = Logger.getLogger(OldWordRepeatViewModel::class.java.name)
        Log.d("OLDWORD", WordString)
        return WordString
    }

//    val WordString: String = myApp.dictWord.word
//    val TranscryptionString: String = mApp.dictWord.transcrypt
//    val translationString: String = mApp.dictWord.translation
}
