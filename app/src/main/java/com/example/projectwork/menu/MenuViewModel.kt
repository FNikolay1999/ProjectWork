package com.example.projectwork.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectwork.App

class MenuViewModel(app : Application) : AndroidViewModel(app) {
    val repository = (app as App).repository
    val userName : MutableLiveData<String> = MutableLiveData()
    val wordsCount : MutableLiveData<Int> = MutableLiveData()
    val averageMistakes : MutableLiveData<Float> = MutableLiveData()
    val userPhoto : MutableLiveData<String> = MutableLiveData()

}
