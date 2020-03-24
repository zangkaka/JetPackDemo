package com.zang.jetpackdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedalViewModel : ViewModel() {
    var numberOfGoldMedal: MutableLiveData<Int> = MutableLiveData()
    var numberOfSliverMedal: MutableLiveData<Int> = MutableLiveData()

    init {
        numberOfGoldMedal.value = 0
        numberOfSliverMedal.value = 0
    }
}