package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData

abstract class ResultViewModel {
    abstract val infantries: LiveData<String>
    abstract val tanks: LiveData<String>
    abstract val grenades: LiveData<String>
    abstract val flags: LiveData<String>
    abstract val stars: LiveData<String>
    abstract val hits: LiveData<String>
}