package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QtyViewModel : ViewModel() {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    val quantity: LiveData<Int>
        get() = _quantity

    fun decrement() {
        val qty = _quantity.value ?: 0
        _quantity.value = 0.coerceAtLeast(qty - 1)
    }

    fun increment() {
        val qty = _quantity.value ?: 0
        _quantity.value = qty + 1
    }
}