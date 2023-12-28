package com.example.memoirstats.model

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memoirstats.utils.ObservableViewModel

class MemoirViewModel : ObservableViewModel() {
    private val _scenarioMap = MutableLiveData<Map<String, Scenario>>()
    private val _currentScenario = MutableLiveData<String>()
    val scenarioMap: LiveData<Map<String, Scenario>> get() = _scenarioMap
    val currentScenario: LiveData<String> get() = _currentScenario

    fun addScenario(name: String) {
        _currentScenario.value = name
        val entry = Pair(name, Scenario())
        if (_scenarioMap.isInitialized)
            _scenarioMap.value = _scenarioMap.value?.plus(entry)
        else
            _scenarioMap.value = mapOf(entry)
        notifyChange()
    }

    fun addRoll(roll: Roll) {
        val name = _currentScenario.value!!
        _scenarioMap.value = _scenarioMap.value?.get(name)?.let {
            it.rolls.add(roll)
            _scenarioMap.value?.plus(Pair(name, it))
        }
        notifyChange()
    }

    private fun countCurrentSide(side: DiceSide): Int =
        _scenarioMap.value?.get(_currentScenario.value)?.rolls
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0

    @Bindable
    fun getCurrentInfantries(): Int = countCurrentSide(DiceSide.Infantry)
    @Bindable
    fun getCurrentTanks(): Int  = countCurrentSide(DiceSide.Tank)
    @Bindable
    fun getCurrentGrenades(): Int  = countCurrentSide(DiceSide.Grenade)
    @Bindable
    fun getCurrentFlags(): Int  = countCurrentSide(DiceSide.Flag)
    @Bindable
    fun getCurrentStars(): Int  = countCurrentSide(DiceSide.Star)
    @Bindable
    fun getCurrentHits(): Int =
        _scenarioMap.value?.get(_currentScenario.value)?.rolls
            ?.map(Roll::hits)
            ?.sum()
            ?: 0

    private fun countTotalSide(side: DiceSide): Int =
        _scenarioMap.value?.values?.flatMap { it.rolls }
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0

    @Bindable
    fun getTotalInfantries(): Int  = countTotalSide(DiceSide.Infantry)
    @Bindable
    fun getTotalTanks(): Int  = countTotalSide(DiceSide.Tank)
    @Bindable
    fun getTotalGrenades(): Int  = countTotalSide(DiceSide.Grenade)
    @Bindable
    fun getTotalFlags(): Int  = countTotalSide(DiceSide.Flag)
    @Bindable
    fun getTotalStars(): Int  = countTotalSide(DiceSide.Star)
    @Bindable
    fun getTotalHits(): Int =
        _scenarioMap.value?.values?.flatMap { it.rolls }
            ?.map(Roll::hits)
            ?.sum()
            ?: 0
}