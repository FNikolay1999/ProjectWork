package com.example.projectwork.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.projectwork.App
import com.example.projectwork.Repository
import com.example.projectwork.database.PolyglotDatabaseDao
import kotlinx.coroutines.*

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

    var mApp = App()
    private var okWords = database.getStudiedWords(mApp.currentLanguage)

    val oldWordsButtonVisible = Transformations.map(okWords) {
        it?.isNotEmpty()
    }
//
//    init {
//        initializeTonight()
//    }
//
//    private fun initializeTonight() {
//        uiScope.launch {
//            tonight.value = getTonightFromDatabase()
//        }
//    }

//    /**
//     *  Handling the case of the stopped app or forgotten recording,
//     *  the start and end times will be the same.j
//     *
//     *  If the start time and end time are not the same, then we do not have an unfinished
//     *  recording.
//     */
//    private suspend fun getTonightFromDatabase(): SleepNight? {
//        return withContext(Dispatchers.IO) {
//            var night = database.getTonight()
//            if (night?.endTimeMilli != night?.startTimeMilli) {
//                night = null
//            }
//            night
//        }
//    }
//
//    private suspend fun clear() {
//        withContext(Dispatchers.IO) {
//            database.clear()
//        }
//    }
//
//    private suspend fun update(night: SleepNight) {
//        withContext(Dispatchers.IO) {
//            database.update(night)
//        }
//    }
//
//    private suspend fun insert(night: SleepNight) {
//        withContext(Dispatchers.IO) {
//            database.insert(night)
//        }
//    }
//
//    /**
//     * Executes when the START button is clicked.
//     */
//    fun onStartTracking() {
//        uiScope.launch {
//            // Create a new night, which captures the current time,
//            // and insert it into the database.
//            val newNight = SleepNight()
//
//            insert(newNight)
//
//            tonight.value = getTonightFromDatabase()
//        }
//    }
//
//    /**
//     * Executes when the STOP button is clicked.
//     */
//    fun onStopTracking() {
//        uiScope.launch {
//            // In Kotlin, the return@label syntax is used for specifying which function among
//            // several nested ones this statement returns from.
//            // In this case, we are specifying to return from launch(),
//            // not the lambda.
//            val oldNight = tonight.value ?: return@launch
//
//            // Update the night in the database to add the end time.
//            oldNight.endTimeMilli = System.currentTimeMillis()
//
//            update(oldNight)
//
//            // Set state to navigate to the SleepQualityFragment.
//            _navigateToSleepQuality.value = oldNight
//        }
//    }
//
//    /**
//     * Executes when the CLEAR button is clicked.
//     */
//    fun onClear() {
//        uiScope.launch {
//            // Clear the database table.
//            clear()
//
//            // And clear tonight since it's no longer in the database
//            tonight.value = null
//        }
//
//        // Show a snackbar message, because it's friendly.
//        _showSnackbarEvent.value = true
//    }

    /**
     * Called when the ViewModel is dismantled.
     * At this point, we want to cancel all coroutines;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}