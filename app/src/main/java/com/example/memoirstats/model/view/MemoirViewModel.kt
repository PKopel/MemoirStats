package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Roll
import com.example.memoirstats.model.Scenario

class MemoirViewModel : ViewModel() {
    private val _scenarioMap = MutableLiveData<Map<String, Scenario>>()
    private val _currentScenario = MutableLiveData<String>()

    fun addScenario(name: String) {
        _currentScenario.value = name
        val entry = Pair(name, Scenario())
        if (_scenarioMap.isInitialized)
            _scenarioMap.value = _scenarioMap.value?.plus(entry)
        else
            _scenarioMap.value = mapOf(entry)
    }

    fun addRoll(roll: Roll) {
        val name = _currentScenario.value!!
        _scenarioMap.value = _scenarioMap.value?.get(name)?.let {
            it.rolls.add(roll)
            _scenarioMap.value?.plus(Pair(name, it))
        }
    }

    fun currentSide(
        side: DiceSide,
        filter: (Roll) -> Boolean = { true }
    ): LiveData<Int> = MutableLiveData(
        _scenarioMap.value?.get(_currentScenario.value)?.rolls
            ?.filter(filter)
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0
    )

    fun currentHits(filter: (Roll) -> Boolean = { true }): LiveData<Int> =
        MutableLiveData(
            _scenarioMap.value?.get(_currentScenario.value)?.rolls
                ?.filter(filter)
                ?.map(Roll::hits)
                ?.sum()
                ?: 0
        )

    fun totalSide(
        side: DiceSide,
        filter: (Roll) -> Boolean = { true }
    ): LiveData<Int> = MutableLiveData(
        _scenarioMap.value?.values?.flatMap { it.rolls }
            ?.filter(filter)
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0
    )

    fun totalHits(filter: (Roll) -> Boolean = { true }): LiveData<Int> =
        MutableLiveData(
            _scenarioMap.value?.values?.flatMap { it.rolls }
                ?.filter(filter)
                ?.map(Roll::hits)
                ?.sum()
                ?: 0
        )
}